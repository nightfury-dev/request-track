import React, { Component } from 'react';
import messages from './messages';
import { FormattedMessage } from 'react-intl';
import { Link } from 'react-router-dom';

class Table extends Component {
  constructor(props) {
    super(props);
    this.state = {
      //infomation of trainee when click modal
      traineeClick: {},
      idTraineeClick: -1,
      //paging in page
      interviewCurrentPage: 1,
      interviewPerPage: 5,

    };
  }

  clickTrainee = (e, id, trainee) => {
    this.setState({ traineeClick: trainee });
  };
  setPageNumber = (e, pageNumber) => {
    this.setState({ interviewCurrentPage: pageNumber });
  };

  render() {
    const { request_interview } = this.props;

    /**
     * paging
     * */
    const { interviewCurrentPage, interviewPerPage } = this.state;

    // Logic for displaying todos
    const indexOfLast = interviewCurrentPage * interviewPerPage;
    const indexOfFirst = indexOfLast - interviewPerPage;
    const interviewsInpage = request_interview.slice(indexOfFirst, indexOfLast);

    // Logic for displaying page numbers
    const pageNumbers = [];
    for (let i = 1; i <= Math.ceil(request_interview.length / interviewPerPage); i++) {
      pageNumbers.push(i);
    }

    return (
      <div className="table-responsive">
        <table className="table table-bordered table-hover table-striped table-vcenter text-center">
          <thead>
          <tr>
            <th scope="row"><FormattedMessage {...messages.no}/></th>
            <th scope="row"><FormattedMessage {...messages.nameRequest}/></th>
            <th scope="row"><FormattedMessage {...messages.language}/></th>
            <th scope="row"><FormattedMessage {...messages.quantity}/></th>
            <th scope="row"><FormattedMessage {...messages.trainee}/></th>
            <th scope="row"><FormattedMessage {...messages.deadline}/></th>
            <th scope="row"><FormattedMessage {...messages.division}/></th>
            <th scope="row"><FormattedMessage {...messages.office}/></th>
            <th scope="row"><FormattedMessage {...messages.status}/></th>
            <th scope="row"><FormattedMessage {...messages.action}/></th>
          </tr>
          </thead>
          <tbody>
          {interviewsInpage.map((item, idRow) =>
            <tr key={item.id}>
              <td>{interviewPerPage * (interviewCurrentPage - 1) + (idRow + 1)}</td>
              <td className="visible-lg">{item.createdBy.name}
              </td>
              <td>{item.language}</td>
              <td>{item.quantity}</td>
              <td>
                {(item.traineeForRequests.length > 0 && item.status != 'Waiting') ?
                  <button data-toggle="modal" data-target="#seeTranee"
                          onClick={(e) => this.clickTrainee(e, item.id, item.traineeForRequests)}>
                    <i className="fa fa-eye text-warning"></i>
                  </button>
                  :
                  <button data-toggle="modal">
                    <i className="fa fa-eye text-dark"></i>
                  </button>
                }
                <sup
                  className="badge badge-primary badge-pill">{item.traineeForRequests && item.traineeForRequests.length}</sup>
              </td>
              <td><span className="font-weight-bold">{item.deadline}</span></td>
              <td className="text-primary">{item.division}</td>
              <td>Đà Nẵng</td>
              <td><span className="badge badge-warning">{item.status}</span></td>
              <td>
                <div className="btn-group btn-group-xs">
                  <button className="btn btn-default px-0">
                    <Link to={`request/${item.id}`}>
                      <i className="fa fa-eye text-warning">
                      </i>
                    </Link>
                  </button>
                </div>
                <div className="btn-group btn-group-xs">
                  <button className="btn btn-default px-0" data-toggle="modal" data-target="#delete">
                    <div className="fa fa-trash text-danger"></div>
                  </button>
                </div>
              </td>
            </tr>,
          )}
          </tbody>
        </table>
        {/*paging*/}
        <nav aria-label="Page navigation example">
          <ul className="pagination">
            {pageNumbers.map(page => {
              return (
                (page == interviewCurrentPage) ?
                  <li key={page} className="page-item pr-2 active" onClick={(e) => this.setPageNumber(e, page)}><a
                    className="page-link"
                    href="#">{page}</a>
                  </li>
                  :
                  <li key={page} className="page-item pr-2 " onClick={(e) => this.setPageNumber(e, page)}><a
                    className="page-link"
                    href="#">{page}</a>
                  </li>
              );
            })}
          </ul>
        </nav>

        {/*when click tranee , see it */}
        <div className="modal fade" id="seeTranee" tabIndex="-1" role="dialog"
             aria-hidden="true">
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-header"><h5 className="modal-title">Infor of trainee</h5>
                <button className="close" type="button" data-dismiss="modal" aria-label="Close"><span
                  aria-hidden="true">×</span></button>
              </div>

              <div className="modal-body">
                <div className="table-responsive">
                  <table className="table table-bordered table-hover table-striped table-vcenter text-center">
                    <thead>
                    <tr>
                      <th><FormattedMessage {...messages.no}/></th>
                      <th><FormattedMessage {...messages.name}/></th>
                      <th><FormattedMessage {...messages.office}/></th>
                      <th><FormattedMessage {...messages.status}/></th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.traineeClick.length > 0 && this.state.traineeClick.map(item =>
                      <tr>
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                        <td>{item.office}</td>
                        <td>{item.status}</td>
                      </tr>,
                    )}
                    </tbody>

                  </table>
                </div>
              </div>
              <div className="modal-footer">
                <button className="btn btn-secondary" type="button" data-dismiss="modal">
                  <FormattedMessage {...messages.modalButtonCancel}/></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Table;

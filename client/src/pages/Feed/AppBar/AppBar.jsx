import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook } from "@fortawesome/free-brands-svg-icons";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { Dropdown } from "react-bootstrap";
import "./AppBar.css";
import { useState } from "react";
import { faHouse } from "@fortawesome/free-solid-svg-icons";

const AppBar = () => {
  return (
    <div
      className="bg-white d-flex align-items-center fixed-top shadow"
      style={{ minHeight: "56px", zIndex: "5" }}
    >
      <div className="container-fluid">
        <div className="row align-items-center">
          {/* Search */}
          <div className="col d-flex align-items-center">
            {/* Logo */}
            <FontAwesomeIcon
              className="text-primary"
              style={{ fontSize: "3rem" }}
              icon={faFacebook}
            />
            {/* Search Bar */}
            <div className="input-group ms-2">
              <Dropdown className="custom-dropdown custom-toggle input-group-prepend d-lg-block">
                <Dropdown.Toggle>
                  <div
                    className="input-group-text  bg-gray border-0 rounded-pill"
                    style={{ minHeight: "40px", minWidth: "230px" }}
                  >
                    <FontAwesomeIcon
                      className="me-2  text-muted"
                      icon={faMagnifyingGlass}
                    />
                    <p className="m-0 fs-7  text-muted">Search Facebook</p>
                  </div>
                </Dropdown.Toggle>
                <Dropdown.Menu
                  className="custom-menu  dropdown-menu overflow-auto  border-0 shadow p-3"
                  style={{
                    width: "20em",
                    maxHeight: "600px",
                    borderRadius: "10px",
                  }}
                >
                  <Dropdown.Item
                    className="my-4"
                    style={{ outline: "none !important" }}
                  >
                    <div
                      className="
                      alert
                      fade
                      show
                      dropdown-item
                      p-1
                      m-0
                      d-flex
                      align-items-center
                      justify-content-between
                      rounded-pill
                      "
                      style={{ backgroundColor: "transparent" }}
                      role="alert"
                    >
                      <div className="d-flex align-items-center custom-close">
                        <img
                          src="https://source.unsplash.com/random/1"
                          alt="avatar"
                          className="rounded-circle me-2"
                          style={{
                            width: "35px",
                            height: "35px",
                            objectFit: "cover",
                          }}
                        />
                        <p class="m-0">Lorem ipsum</p>
                      </div>
                    </div>
                  </Dropdown.Item>

                  {/* item2 */}

                  <Dropdown.Item className="my-4">
                    <div
                      class="
                      alert
                      fade
                      show
                      dropdown-item
                      p-1
                      m-0
                      d-flex
                      align-items-center
                      justify-content-between
                    "
                      style={{ backgroundColor: "transparent" }}
                      role="alert"
                    >
                      <div className="d-flex align-items-center">
                        <img
                          src="https://source.unsplash.com/random/2"
                          alt="avatar"
                          className="rounded-circle me-2"
                          style={{
                            width: "35px",
                            height: "35px",
                            objectFit: "cover",
                          }}
                        />
                        <p className="m-0">Lorem ipsum</p>
                      </div>
                    </div>
                  </Dropdown.Item>

                  {/* item3 */}

                  <Dropdown.Item className="my-4">
                    <div
                      class="
                      alert
                      fade
                      show
                      dropdown-item
                      p-1
                      m-0
                      d-flex
                      align-items-center
                      justify-content-between
                    "
                      style={{ backgroundColor: "transparent" }}
                      role="alert"
                    >
                      <div className="d-flex align-items-center">
                        <img
                          src="https://source.unsplash.com/random/3"
                          alt="avatar"
                          className="rounded-circle me-2"
                          style={{
                            width: "35px",
                            height: "35px",
                            objectFit: "cover",
                          }}
                        />
                        <p className="m-0">Lorem ipsum</p>
                      </div>

                      {/* <button
                      type="button"
                      className="btn-close p-0 m-1 custom-close"
                      data-bs-dismiss="alert"
                      
                    ></button> */}
                    </div>
                  </Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
              {/* search drop down */}
            </div>
          </div>
          {/* nav */}
        </div>
        <div className="col d-none d-xl-flex justify-content-center">
      {/* home */}
      <div className="mx-4 nav__btn nav__btn-active">
        <button type="button" className="btn px-4">
        <FontAwesomeIcon icon={faHouse} />
        </button>
      </div>
      {/* market */}
      <div className="mx-4 nav__btn">
        <button type="button" className="btn px-4">
        <FontAwesomeIcon icon={faHouse} style={{color: "#001eff",}} />
        </button>
      </div>
      {/* group */}
      <div className="mx-4 nav__btn">
        <button type="button" className="btn px-4">
          <i
            type="button"
            className="position-relative fas fa-users text-muted fs-4"
          >
            <span
              className="
                position-absolute
                top-0
                start-100
                translate-middle
                badge
                rounded-pill
                bg-danger
              "
              style={{ fontSize: '0.5rem' }}
            >
              1
              <span className="visually-hidden"></span>
            </span>
          </i>
        </button>
      </div>
      {/* gaming */}
      <div className="mx-4 nav__btn">
        <button type="button" className="btn px-4">
        <FontAwesomeIcon icon={faHouse} style={{color: "#001eff",}} />
        </button>
      </div>
    </div>
      </div>
    </div>
  );
};

export default AppBar;

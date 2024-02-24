import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./UserInformation.css";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

const UserInformation = () => {
  const [userInformation, setUserInformation] = useState({
    dateOfBirth: "",
    gender: "",
    phoneNumber: "",
  });

  return (
    <>
      <div className="information">
        <div className="informationWrapper">
          <div className="informationLeft">
            <div className="facebookLogo">
              <img
                className="fb_logo _8ilh img"
                src="https://static.xx.fbcdn.net/rsrc.php/y1/r/4lCu2zih0ca.svg"
                alt="Facebook"
              ></img>
            </div>
          </div>
          <div className="informationRight">
            <div className="informationBox">
              <form className="form">
                <div>
                <LocalizationProvider  dateAdapter={AdapterDayjs}>
                  <DemoContainer  components={["DatePicker"]}>
                    <DatePicker  label="Date of birth" style={{width:'100%', backgroundColor: '#000000', cursor: 'not-allowed' }}/>
                  </DemoContainer>
                </LocalizationProvider>
                </div>
                <div className="informationUsername">
                  <input
                    className="informationInput"
                    required
                    name="firstName"
                    placeholder="First Name"
                    type="text"
                    id="firstName"
                  />
                  <span className="error-message"></span>
                </div>

                <div className="informationSurname">
                  <input
                    className="informationInput"
                    required
                    name="lastName"
                    placeholder="Surname"
                    type="text"
                    id="surname"
                  />
                  <span className="error-message"></span>
                </div>

                <div className="informationSubmit">
                  <input id="informationBtn" type="submit" value="Sign Up" />
                </div>
                <hr className="informationHr" />
                <div className="informationLoginAc">
                  <label htmlFor="reg-btn"></label>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default UserInformation;

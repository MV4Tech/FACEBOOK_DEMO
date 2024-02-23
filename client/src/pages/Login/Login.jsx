import React from "react";
import "./login.css";
import { useState } from 'react'
import { Link,useNavigate } from "react-router-dom";
import authService from '../../services/auth-service'


function Login() {
 
  const navigate = useNavigate();

  const [loginRequest, setLoginRequest] = useState(
    {
      email: "",
      password: ""
    }
  )


  const [invalidCredentialsMessage, setInvalidCredentialsMessage] = useState(null);
  const [notEnabledAcc, setNotEnabledAcc] = useState(null);


  const handleChange = (e) => {
    const value = e.target.value;
    setLoginRequest({...loginRequest,[e.target.name]: value})
    setInvalidCredentialsMessage(null);
  
}


const submit = async (e) => {
  e.preventDefault();
  try {
      await authService.makeLoginRequest(loginRequest);
     
      navigate("/");
  } catch (error) {
    console.log(error.response.data.errors);
     const errorMessage = error.response.data.errors[0];
  if(errorMessage === "Invalid email or password!"){
    setInvalidCredentialsMessage("Invalid email or password!");
    setLoginRequest(prevLoginRequest => ({ ...prevLoginRequest, password: '' , email: ''}));
  }
  }
};


  return (
  
<>
    <div className="login">
      <div className="loginWrapper">
        <div className="loginLeft">
          <div className="facebookLogo">
            <img className="fb_logo _8ilh img" src="https://static.xx.fbcdn.net/rsrc.php/y1/r/4lCu2zih0ca.svg" alt="Facebook"></img>
          </div>
          <div className="loginLeftDesc">
          Facebook helps you connect and share with the people in your life. <br />
          </div>
        </div>
        <div className="loginRight">
          <div className="loginBox">
            <form onSubmit={submit} className="loginForm">
              <div className="loginUsername">
                <input
                  required
                  name="email"
                  value={loginRequest.email}
                  className="loginInput"
                  placeholder="Email"
                  type="email"
                  id="email-l"
                  onChange={(e)=>handleChange(e)}
                />
              </div>
              <div className="loginPassword">
                <input
                  className="loginInput"
                  required
                  name="password"
                  placeholder="Password"
                  type="password"
                  value={loginRequest.password}
                  id="password-l"
                  onChange={(e)=>handleChange(e)}
                />
              </div>
              <div className="errors">
                  {invalidCredentialsMessage && <p style={{ color: 'red' , marginBottom:'0px'}}>{invalidCredentialsMessage}</p>}
                  {notEnabledAcc && <p style={{ color: 'red' }}>{notEnabledAcc}</p>}
              </div>
             
              <div className="loginSubmit">
                  <input id="loginBtn" type="submit" value="Log In" />
              </div>
              <span>
                <a className="forgetPwd" href="#email?">
                  Forgotten password?{" "}
                </a>
              </span>
              <hr className="loginHr" />
              <div className="loginCreateAc">
                <Link to="/register">
                  <input
                    className="loginCreateBtn"
                    type="submit"
                    value="Create new account"
                  />
                </Link>
              </div>
            </form>
          </div>
          <div className="loginRightDesc">
            <b>Create a Page </b> for a celebrity, brand or business.
          </div>
        </div>
      </div>
    </div>
    </>
  );
}

export default Login;
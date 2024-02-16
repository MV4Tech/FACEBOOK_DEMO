import React from "react";
import "./login.css";
import { Link } from "react-router-dom";

function Login() {
 



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
            <form action="#" className="loginForm">
              <div className="loginUsername">
                <input
                  className="loginInput"
                  placeholder="Email"
                  type="text"
                  id="username-l"
                />
              </div>
              <div className="loginPassword">
                <input
                  className="loginInput"
                  placeholder="Password"
                  type="password"
                  id="password-l"
                />
              </div>
              <div className="loginSubmit">
                <Link to="/">
                  <input id="loginBtn" type="submit" value="Log In" />
                </Link>
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
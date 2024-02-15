import React from 'react'
import { Link } from 'react-router-dom'
import './register.css'

const Register = () => {
  return (
    <>
    <div className="login">
      <div className="loginWrapper">
        <div className="loginLeft">
          <div className="facebookLogo">
            <img class="fb_logo _8ilh img" src="https://static.xx.fbcdn.net/rsrc.php/y1/r/4lCu2zih0ca.svg" alt="Facebook"></img>
          </div>
          <div className="loginLeftDesc">
          Facebook helps you connect and share with the people in your life. <br />
          </div>
        </div>
        <div className="registerRight">
        <div className="registerBox">
        <form action="" className='registerForm'>

                <div className="registerUsername">
                    <input className='registerInput' placeholder='First Name' type="text" id="firstName"/>
                </div>

                <div className="registerSurname">
                    <input className='registerInput' placeholder='Surname' type="text" id="surname"/>
                </div>
        
                <div className="registerEmail">
                    <input className='registerInput' placeholder='Email' type="text" id="email"/>
                </div>
                
                <div className="registerPassword">
                    <input className='registerInput' placeholder='Password' type="password" id="password"/>
                </div>
                <div className="registerPassword">
                    <input className='registerInput' placeholder='Re-enter password' type="password" id="re-Password"/>
                </div>
                <div className="registerSubmit">
                    <input id='registerBtn' type="submit" value="Sign Up"/>
                </div>
                <hr className="registerHr" />
                <div className="registerLoginAc">
                
                    <label for="reg-btn">
                            <Link  style={{textDecoration:'none'}} to="/login">
                    <span className="alrdy-have-acc">Already have a account?</span>
                    </Link>
                    </label>       
                    </div>
            </form>
        </div>
        <div className="registerRightDesc">
          <b>Create a Page </b> for a celebrity, brand or business.
        </div>
    </div>
      </div>
    </div>
    </>
    /*
    <div className="register">
    <div className="registerWrapper">
        <div className="registerLeft">
            <h3 className="registerLogo">fchatSocial</h3>
            <div className="registerLeftDesc">
             Connect with friends and the world <br /> around you on Fchatsocial.
            </div>
        </div>
        <div className="registerRight">
        <div className="registerBox">
        <form action="" className='registerForm'>
                <div class="registerUsername">
                    <input className='registerInput' placeholder='Username' type="text" id="username-l"/>
                </div>
                <div class="registerEmail">
                    <input className='registerInput' placeholder='Email or Phone number' type="text" id="username-l"/>
                </div>
                <div class="registerPassword">
                    <input className='registerInput' placeholder='Password' type="password" id="password-R"/>
                </div>
                <div class="registerPassword">
                    <input className='registerInput' placeholder='Retype-password' type="password" id="re-Password-R"/>
                </div>
                <div class="registerSubmit">
                    <input id='registerBtn' type="submit" value="Sign Up"/>
                </div>
                <hr className="registerHr" />
                <div class="registerLoginAc">
                
                    <label for="reg-btn">
                            <Link  style={{textDecoration:'none'}} to="/login">
                    <span >Already have a account?</span>
                    </Link>
                    </label>       
                    </div>
            </form>
        </div>
        <div className="registerRightDesc">
          <b>Create a Page </b> for a celebrity, brand or business.
        </div>
    </div>
    </div>
</div>
*/
  )
}

export default Register
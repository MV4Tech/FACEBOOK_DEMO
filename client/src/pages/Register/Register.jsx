import React from 'react'
import { Link,useNavigate } from 'react-router-dom'
import './register.css'
import { useState } from 'react'
import authService from '../../services/auth-service'

const Register = () => {
  


  const [user, setUser] = useState({
    firstName:"",
    lastName:"",
    password:"",
    confirmPassword:"",
    email:"",
    role:"USER"
})

const handleChange = (e) => {
  const value = e.target.value;
  setUser({...user,[e.target.name]:value})
}


let navigate = useNavigate();

const saveUser = async (e) => {
  e.preventDefault();
  try {
    await authService.makeRegisterRequest(user);
    navigate("/login");
  } catch (error) {
    if (error.response && error.response.data && error.response.data.errors) {
      // If the errors array is available from the backend
      const errorMessage = error.response.data.errors[0]; // Assuming there's only one error returned
      alert(errorMessage);
    } else if (error.message.includes('Duplicate entry')) {
      // If it's a duplicate entry error
      alert('Email already exists. Please use a different email.');
    } else {
      // For other types of errors
      alert('An error occurred. Please try again later.');
    }
  }
}

// TODO: implement proper error handling for the registration process



  return (
    <>
    <div className="register">
      <div className="registerWrapper">
        <div className="registerLeft">
          <div className="facebookLogo">
            <img className="fb_logo _8ilh img" src="https://static.xx.fbcdn.net/rsrc.php/y1/r/4lCu2zih0ca.svg" alt="Facebook"></img>
          </div>
          <div className="registerLeftDesc">
          Facebook helps you connect and share with the people in your life. <br />
          </div>
        </div>
        <div className="registerRight">
        <div className="registerBox">
        <form onSubmit={saveUser} className='registerForm'>

                <div className="registerUsername">
                    <input className='registerInput'
                    required
                     name="firstName"
                      value={user.firstName}
                       placeholder='First Name'
                        type="text"
                         id="firstName"
                         onChange={(e)=>handleChange(e)}
                         />
                         
                </div>

                <div className="registerSurname">
                    <input className='registerInput'
                    required
                     name="lastName"
                      value={user.lastName}
                       placeholder='Surname'
                        type="text"
                         id="surname"
                         onChange={(e)=>handleChange(e)}
                         />
                         
                </div>
        
                <div className="registerEmail">
                    <input className='registerInput'
                    required
                     name="email"
                      value={user.email}
                      placeholder='Email'
                       type="email"
                        id="email"
                        onChange={(e)=>handleChange(e)}
                        />
                        
                </div>
                
                <div className="registerPassword">
                    <input className='registerInput'
                    required
                     name="password"
                      value={user.password}
                       placeholder='Password'
                        type="password"
                         id="password"
                         onChange={(e)=>handleChange(e)}
                         />
                         
                </div>

                <div className="registerPassword">
                    <input className='registerInput'
                     name="confirmPassword"
                      value={user.confirmPassword}
                       placeholder='Re-enter password'
                        type="password"
                         id="re-Password"
                         onChange={(e)=>handleChange(e)}
                         />
                        
                </div>

                <div className="registerSubmit">
                    
                    <input id='registerBtn' type="submit" value="Sign Up"/>
                   
                </div>
                
                <hr className="registerHr" />
                <div className="registerLoginAc">
                
                    <label htmlFor="reg-btn">
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
  )
}

export default Register
import { useState } from 'react'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import AuthGuardWhenLogout from './components/AuthGuardWhenLogout';
import AuthGuardWhenLogin from './components/AuthGuardWhenLogin';
import Home from './pages/Home';
import Login from './pages/Login/Login';
import Register from './pages/Register/Register';

const App = createBrowserRouter(
  createRoutesFromElements(
    <>
        <Route element={<AuthGuardWhenLogout />}>
          <Route path="/">
            <Route index element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
          </Route>

        </Route>
           <Route element={<AuthGuardWhenLogin />}>
          
        </Route>
    </>
  )
);

export default App

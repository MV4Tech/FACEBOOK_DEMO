import { useState } from 'react'
import './App.css'
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import AuthGuardWhenLogout from './components/AuthGuardWhenLogout';
import AuthGuardWhenLogin from './components/AuthGuardWhenLogin';
import Home from './pages/Home';

const App = createBrowserRouter(
  createRoutesFromElements(
    <>
        <Route element={<AuthGuardWhenLogout />}>
          <Route path="/">
            <Route index element={<Home />} />
          </Route>

        </Route>
           <Route element={<AuthGuardWhenLogin />}>
          
        </Route>
    </>
  )
);

export default App

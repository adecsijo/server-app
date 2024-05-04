import React from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Registration from "./components/Registration.jsx";
import Login from "./components/Login.jsx";
import LandingPage from "./components/LandingPage.jsx";
import AuthProvider from "./components/AuthProvider.jsx";
import AllScreen from "./components/AllScreen.jsx";

function App() {
  return (
      <Router>
        <AuthProvider>
          <Routes>
            <Route path="/" element={<LandingPage />} />
            <Route path="/registration" element={<Registration />} />
            <Route path="/login" element={<Login />} />
            <Route path="/dashboard" element={<AllScreen />} />
          </Routes>
        </AuthProvider>
      </Router>
  );
}

export default App

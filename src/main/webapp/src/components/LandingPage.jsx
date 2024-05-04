import React from "react";
import { Link } from "react-router-dom";
import './styles.css'

const LandingPage = () => {
  return (
      <div className="container">
        <h2>Welcome to Our Website</h2>
        <p>Please select an option:</p>
        <div className="links-container">
          <Link to="/login"><button>Login</button></Link>
          <Link to="/registration"><button>Registration</button></Link>
        </div>
      </div>
  );
};

export default LandingPage;

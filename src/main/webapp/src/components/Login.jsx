import React, {useContext, useState} from "react";
import { AuthContext } from "./AuthProvider";
import './styles.css'

const Login = () => {
  const { login } = useContext(AuthContext);
  const [formData, setFormData] = useState({
    username: "",
    password: ""
  });
  const [loginError, setLoginError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await login(formData);
    } catch (error) {
      setLoginError(error.message);
      console.log(error.message);
    }
  };

  return (
      <div className="container">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="username">Username: </label>
            <input
                type="text"
                id="username"
                name="username"
                value={formData.username}
                onChange={handleChange}
            />
          </div>
          <div>
            <label htmlFor="password">Password: </label>
            <input
                type="password"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
            />
          </div>
          <button type="submit">Login</button>
          {loginError && <p>{loginError}</p>}
        </form>
      </div>
  );
};

export default Login;

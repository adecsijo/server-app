import React, { createContext, useEffect, useState } from "react";

export const AuthContext = createContext({
  isLoggedIn: false,
  login: () => {},
  logout: () => {}
});

const AuthProvider = ({ children }) => {

  const login = async (formData) => {
    try {
      const response = await fetch("http://localhost:8080/user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
      });

      if (response.ok) {
        const data = await response.text();
        localStorage.setItem("auth", data);
        window.location.href = "/dashboard";
        return true;
      } else {
        const data = await response.json();
        throw new Error(data.message);
      }
    } catch (error) {
      throw new Error("Login failed: " + error.message);
    }
  };

  const logout = () => {
    localStorage.removeItem("auth");
    window.location.href = "/";
  };

  const ctx = {
    isLoggedIn: localStorage.getItem("auth") !== null,
    login: login,
    logout: logout
  };

  return (
      <AuthContext.Provider value={ctx}>
        {children}
      </AuthContext.Provider>
  );
};

export default AuthProvider;

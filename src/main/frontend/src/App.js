import './App.css';
import axios from "axios";
import React,{ useEffect, useState } from "react";

// this is functional component
const UserProfiles = () => {

  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      setUserProfiles(res.data);
    })
  }

  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index) => {
    return(
      <div key={index.toString()}>
        <h2>{userProfile.username}</h2>
        <p>{userProfile.id}</p>
      </div>
    )
  });
};

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;

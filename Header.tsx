import '../css/MovieDet.css'

import React from 'react';
import { Link } from 'react-router-dom';
import '../css/MovieDet.css';
const Header = () => {

    return (
        <div className = "home">
            <h1>Movie Explorer App</h1>
    <nav>
    <Link to = "/">Home</Link>
      <Link to = "/top-rated">Top Rated</Link>
        <Link to = "/search">Search</Link>
        </nav>
        </div>
);
}
export default Header;
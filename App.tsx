import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Home from './pages/Home';
import TopRated from './pages/TopRated';
import Search from './pages/Search';
import MovieDetail from './components/MovieDetails';

const App: React.FC = () => {
  return (
    <Router>
      <div>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/top-rated" element={<TopRated />} />
          <Route path="/search" element={<Search />} />
          <Route path="/details" element={<MovieDetail />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;

import React from 'react';
import { useLocation } from 'react-router-dom';
import { allMovieDescription } from './MovieList';
import '../css/MovieDet.css';

interface MovieDetailProps {
  movie: {
    id: number;
    title: string;
    image: string;
    description: string;
    releaseYear: string;
  };
}

const MovieDetail= () => {
  const location = useLocation();
  const { id } = location.state;

  const selectedMovie = allMovieDescription.find((movie) => movie.id === id);

  if (!selectedMovie) {
    return <div>Error: Movie not found</div>;
  }

  return (
    <>
      <h1 className="title">{selectedMovie.title}</h1>
      <center>
        <img className="movieDetails" src={selectedMovie.image} alt={selectedMovie.title} />
      </center>
      <p className="movieDetails">{selectedMovie.description}</p>
      <p className="movieDetails">Release Year: {selectedMovie.releaseYear}</p>
    </>
  );
};

export default MovieDetail;

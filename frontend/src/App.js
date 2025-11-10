import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import DealerList from './components/DealerList';
import VehicleList from './components/VehicleList';


function App() {
  return (
    <Router>
      <div className="container mt-3">
        <nav className="mb-3">
          <Link className="btn btn-outline-primary me-2" to="/dealers">Dealers</Link>
          <Link className="btn btn-outline-primary mr-2" to="/vehicles">Vehicles</Link>
        </nav>
        <Routes>
          <Route path="/dealers" element={<DealerList/>}></Route>
          <Route path="/vehicles" element={<VehicleList/>}></Route>
        </Routes>
      </div>
    </Router>
  )
}

export default App;

import React, { useEffect, useState } from 'react';
import { api } from '../api';


const VehicleList = () => {

  const [vehicles, setVehicles] = useState([]);
  const [dealers, setDealers] = useState([]);
  const [form, setForm] = useState({
    model: '',
    price: '',
    status: 'AVAILABLE',
    dealerId: ''
  });

   useEffect(() => {
    loadVehicles();
    loadDealers();
  }, []);

    
  const loadVehicles = async () => {
    try {
      const res = await api.get('/vehicles', {
        headers: { 'Cache-Control': 'no-cache' }
      });
      setVehicles(res.data);
    } catch (error) {
      console.error("Error loading vehicles", error);
      alert("Failed to load vehicles");
    }
  };

  
  const loadDealers = async () => {
    try {
      const res = await api.get('/dealers', {
        headers: { 'Cache-Control': 'no-cache' }
      });
      setDealers(res.data);
    } catch (error) {
      console.error("Error loading dealers", error);
      alert("Failed to load dealers for dropdown");
    }
  };

  
  const addVehicle = async () => {
    if (!form.model || !form.price || !form.dealerId) {
      alert("Please fill all fields");
      return;
    }
    try {
      
      const payload = {
        model: form.model,
        price: parseFloat(form.price),
        status: form.status,
        dealer: { id: form.dealerId } 
      };
      await api.post('/vehicles/addVehicle', payload);
      setForm({ model: '', price: '', status: 'AVAILABLE', dealerId: '' });
      loadVehicles();
    } catch (error) {
      console.error("Error adding vehicle", error);
      alert("Failed to add vehicle");
    }
  };

  
  const deleteVehicle = async (id) => {
        if (!window.confirm("Are you sure to delete this vehicle?")) return;
        try {
        await api.delete(`/vehicles/delete/${id}`);
        loadVehicles();
        } catch (error) {
        console.error("Error deleting vehicle", error);
        alert("Failed to delete vehicle");
        }
    };

  
  const loadPremiumVehicles = async () => {
    try {
      const res = await api.get('/vehicles/premium', {
        headers: { 'Cache-Control': 'no-cache' }
      });
      setVehicles(res.data);
    } catch (error) {
      console.error("Error loading premium vehicles", error);
      alert("Failed to load premium dealer vehicles");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Vehicle Management System</h2>

      <div className="mb-3">
            <input
            type="text"
            className="form-control mb-2"
            placeholder="Model"
            value={form.model}
            onChange={e => setForm({ ...form, model: e.target.value })}
            />
            <input
            type="number"
            className="form-control mb-2"
            placeholder="Price"
            value={form.price}
            onChange={e => setForm({ ...form, price: e.target.value })}
            />
            <select
            className="form-control mb-2"
            value={form.status}
            onChange={e => setForm({ ...form, status: e.target.value })}
            >
            <option value="AVAILABLE">AVAILABLE</option>
            <option value="SOLD">SOLD</option>
            </select>
            <select
            className="form-control mb-2"
            value={form.dealerId}
            onChange={e => setForm({ ...form, dealerId: e.target.value })}
            >
            <option value="">Select Dealer</option>
            {dealers.map(d => (
                <option key={d.id} value={d.id}>
                {d.name} ({d.subscriptionType})
                </option>
            ))}
            </select>
            <button className="btn btn-primary me-2" onClick={addVehicle}>Add Vehicle</button>
            <button className="btn btn-outline-success" onClick={loadPremiumVehicles}>Premium Vehicles</button>
      </div>
      
      <table className="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>MODEL</th>
                    <th>PRICE</th>
                    <th>STATUS</th>
                    <th>DEALER</th>
                    <th>DEALER SUBSCRIPTION</th>
                    <th>ACTION</th>
                </tr>
            </thead>
            <tbody>
                {vehicles.map(v => (
                    <tr key={v.id}>
                    <td>{v.id}</td>
                    <td>{v.model}</td>
                    <td>{v.price}</td>
                    <td>{v.status}</td>
                    <td>{dealers.find(d => d.id === v.dealer?.id)?.name || 'N/A'}</td>
                    <td>{dealers.find(d => d.id === v.dealer?.id)?.subscriptionType || '-'}</td>
                    <td>
                        <button className="btn btn-danger btn-sm" onClick={() => deleteVehicle(v.id)}>Delete</button>
                    </td>
                    </tr>
                ))}
            </tbody>
      </table>
    </div>
  )
}

export default VehicleList

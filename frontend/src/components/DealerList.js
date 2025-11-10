import React, { useEffect, useState } from 'react';
import {api} from '../api';

function DealerList() {

    const [dealers,setDealers] = useState([]);
    const [form,setForm] = useState({name : '', email : '', subscriptionType: 'BASIC'});

    useEffect(()=>{
        loadDealers();
    },[]);

    const loadDealers = async () =>{
        try {
            const res = await api.get('/dealers',{
                headers : {'Cache-Control': 'no-cache'}
        });
            setDealers(res.data);
        } catch (error) {
            console.error("Error in loading dealers ", error);
            alert("Failed to load Dealers");
        }
    }

    const addDealer = async () =>{
        if(!form.name || !form.email){
            alert("Please fill name and email");
            return;
        }
        try {
           await api.post('/dealers/addDealer', form);
           setForm({name : '', email : '', subscriptionType: 'BASIC'});
           loadDealers();
        } catch (error) {
            console.error("Error in adding Dealer", error);
            alert("Failed to add Dealer");
        }
    }

    const deleteDealer = async (id) =>{
        if(!window.confirm("Are you sure to delete the Dealer ?")){
            return;
        }
        try {
            await api.delete(`/dealers/delete/${id}`);
            loadDealers();
        } catch (error) {
            console.error("Error in deleting Dealer", error);
            alert("Failed to delete the Dealer");
        }
    }

  return (
    <div className="container mt-4">
        <h2>Dealer Management System</h2>
        <div className="mb-3">
            <input type="text" className="form-control mb-2" placeholder="Name" value={form.name} onChange={e =>setForm({...form, name: e.target.value})}/>
            <input type="email" className="form-control mb-2" placeholder="Email" value={form.email} onChange={e =>setForm({...form, email: e.target.value})}/>
            <select name="subscription" id="subscription" className='form-control mb-2' value={form.subscriptionType} onChange={e =>setForm({...form, subscriptionType: e.target.value})}>
                <option value="BASIC">BASIC</option>
                <option value="PREMIUM">PREMIUM</option>
            </select>
            <button className="btn btn-primary" onClick={addDealer}>Add Dealer</button>
        </div>

        {/* Dealer table */}
        <table className="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>EMAIL</th>
                    <th>SUBSCRIPTION</th>
                    <th>ACTION</th>
                </tr>
            </thead>
            <tbody>
                {dealers.map(d =>(
                    <tr key={d.id}>
                        <td>{d.id}</td> 
                        <td>{d.name}</td>
                        <td>{d.email}</td>
                        <td>{d.subscriptionType}</td>
                        <td>
                            <button className="btn btn-danger btn-sm" onClick={() => deleteDealer(d.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    </div>
  )
}

export default DealerList

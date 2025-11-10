import axios from 'axios';

const BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:9075';

export const api = axios.create({
    baseURL : BASE_URL
});
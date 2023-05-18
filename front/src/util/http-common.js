import axios from "axios";

export default axios.create({
    baseURL: "http://70.12.50.48:8080/enjoy-trip",
    headers:{
        "Content-type": "application/json"
    },
});

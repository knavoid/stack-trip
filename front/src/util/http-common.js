import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080/enjoy-trip",
    headers: {
        "Content-type": "application/json",
    },
});

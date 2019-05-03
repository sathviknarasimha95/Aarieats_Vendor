package com.example.aarieats.http.models;

public class LoginResponse {
    String error;

    Data data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        String status;
        Double vendorLat;
        Double vendorLng;
        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setVendorLat(Double lat) {
            vendorLat = lat;
        }

        public void setVendorLng(Double lng) {
            vendorLng = lng;
        }

        public Double getVendorLat() {
            return vendorLat;
        }

        public Double getVendorLng() {
            return vendorLng;
        }
    }
}

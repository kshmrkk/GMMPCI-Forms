import React, { useState } from 'react';
import axios from 'axios';

const ComplianceForm = () => {
  const [formData, setFormData] = useState({
    jobOrderNo: '',
    nameOfDeceased: { firstName: '', middleName: '', surname: '' },
    dateOfInterment: '',
    dayTime: '',
    location: '',
    remarks: '',
    jobOrderReleasedBy: '',
    marketingDepartmentDate: '',
    marketingDepartmentTime: '',
    jobOrderReceivedBy: '',
    parkOpsManagerSignature: '',
    parkOpsStaffSignature: '',
    // Add other fields as per your form requirements
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8081/api/compliance/generate', formData, { responseType: 'blob' });
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'compliance_form.pdf');
      document.body.appendChild(link);
      link.click();
    } catch (error) {
      console.error('Error generating PDF', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Job Order No.:</label>
        <input type="text" name="jobOrderNo" onChange={handleChange} />
      </div>

      <div>
        <label>Name of Deceased:</label>
        <input type="text" name="firstName" placeholder="First Name" onChange={handleChange} />
        <input type="text" name="middleName" placeholder="Middle Name" onChange={handleChange} />
        <input type="text" name="surname" placeholder="Surname" onChange={handleChange} />
      </div>

      <div>
        <label>Date of Interment:</label>
        <input type="date" name="dateOfInterment" onChange={handleChange} />
      </div>

      <div>
        <label>Day/Time:</label>
        <input type="text" name="dayTime" onChange={handleChange} />
      </div>

      <div>
        <label>Location:</label>
        <input type="text" name="location" onChange={handleChange} />
      </div>

      <div>
        <label>Remarks:</label>
        <input type="text" name="remarks" onChange={handleChange} />
      </div>

      <div>
        <label>Job Order Released By:</label>
        <input type="text" name="jobOrderReleasedBy" onChange={handleChange} />
      </div>

      {/* Add remaining fields similarly */}

      <button type="submit">Generate PDF</button>
    </form>
  );
};

export default ComplianceForm;

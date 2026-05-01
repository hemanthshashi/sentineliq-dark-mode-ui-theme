import { useState } from "react";
import { createTool } from "../services/toolService";
import React from "react";

function ToolForm({ onToolCreated }) {
  const [formData, setFormData] = useState({
    name: "",
    category: "",
    description: "",
    websiteUrl: "",
    logoUrl: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    await createTool(formData);

    setFormData({
      name: "",
      category: "",
      description: "",
      websiteUrl: "",
      logoUrl: "",
    });

    onToolCreated();
  };

  return (
    <form onSubmit={handleSubmit} className="border p-4 rounded shadow mb-6">
      <h2 className="text-xl font-bold mb-4">Add New Tool</h2>

      <div className="grid gap-3">
        <input className="border p-2 rounded" name="name" placeholder="Tool Name" value={formData.name} onChange={handleChange} />
        <input className="border p-2 rounded" name="category" placeholder="Category" value={formData.category} onChange={handleChange} />
        <input className="border p-2 rounded" name="description" placeholder="Description" value={formData.description} onChange={handleChange} />
        <input className="border p-2 rounded" name="websiteUrl" placeholder="Website URL" value={formData.websiteUrl} onChange={handleChange} />
        <input className="border p-2 rounded" name="logoUrl" placeholder="Logo URL" value={formData.logoUrl} onChange={handleChange} />

        <button className="bg-green-600 text-white px-4 py-2 rounded">
          Add Tool
        </button>
      </div>
    </form>
  );
}

export default ToolForm;
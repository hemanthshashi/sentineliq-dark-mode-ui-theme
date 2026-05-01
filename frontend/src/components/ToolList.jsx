import React, { useEffect, useState } from "react";
import { searchTools, deleteTool, updateTool } from "../services/toolService";
import ToolForm from "./ToolForm";

function ToolList() {
  const [tools, setTools] = useState([]);
  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [editingTool, setEditingTool] = useState(null);

  const fetchTools = async () => {
    const data = await searchTools({
      name,
      category,
      page: 0,
      size: 10,
      sortBy: "id",
      direction: "asc",
    });

    setTools(data.content || []);
  };

  useEffect(() => {
    fetchTools();
  }, []);

  const handleDelete = async (id) => {
    await deleteTool(id);
    fetchTools();
  };

  const handleEditChange = (e) => {
    setEditingTool({
      ...editingTool,
      [e.target.name]: e.target.value,
    });
  };

  const handleUpdate = async (e) => {
    e.preventDefault();

    await updateTool(editingTool.id, editingTool);
    alert("Tool updated successfully");

    setEditingTool(null);
    fetchTools();
  };

  return (
    <div className="p-6">
      <h2>Tools</h2>

      {!editingTool && <ToolForm onToolCreated={fetchTools} />}

      {editingTool && (
        <form onSubmit={handleUpdate}>
          <h2>Edit Tool</h2>

          <input name="name" value={editingTool.name} onChange={handleEditChange} />
          <input name="category" value={editingTool.category} onChange={handleEditChange} />
          <input name="description" value={editingTool.description} onChange={handleEditChange} />
          <input name="websiteUrl" value={editingTool.websiteUrl} onChange={handleEditChange} />
          <input name="logoUrl" value={editingTool.logoUrl} onChange={handleEditChange} />

          <button type="submit">Update Tool</button>
          <button type="button" onClick={() => setEditingTool(null)}>
            Cancel
          </button>
        </form>
      )}

      <div>
        <input
          placeholder="Search by name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <input
          placeholder="Filter by category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        />

        <button onClick={fetchTools}>Search</button>
      </div>

      <div>
        {tools.length === 0 ? (
          <p>No tools found</p>
        ) : (
          tools.map((tool) => (
            <div key={tool.id}>
              <h3>{tool.name}</h3>
              <p>{tool.category}</p>
              <p>{tool.description}</p>

              <a href={tool.websiteUrl} target="_blank" rel="noreferrer">
                Visit Website
              </a>

              <br />

              <button onClick={() => setEditingTool(tool)}>Edit</button>
              <button onClick={() => handleDelete(tool.id)}>Delete</button>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default ToolList;
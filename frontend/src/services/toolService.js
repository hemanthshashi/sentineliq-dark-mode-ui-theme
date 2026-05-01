const API_BASE_URL = "http://localhost:8081/api/tools";

export const getAllTools = async () => {
  const response = await fetch(API_BASE_URL);
  return response.json();
};

export const searchTools = async ({
  name = "",
  category = "",
  page = 0,
  size = 5,
  sortBy = "id",
  direction = "asc",
}) => {
  const params = new URLSearchParams();

  if (name) params.append("name", name);
  if (category) params.append("category", category);

  params.append("page", page);
  params.append("size", size);
  params.append("sortBy", sortBy);
  params.append("direction", direction);

  const response = await fetch(`${API_BASE_URL}/search?${params.toString()}`);
  return response.json();
};

export const createTool = async (toolData) => {
  const response = await fetch(API_BASE_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(toolData),
  });

  return response.json();
};

export const updateTool = async (id, toolData) => {
  const response = await fetch(`${API_BASE_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(toolData),
  });

  return response.json();
};

export const deleteTool = async (id) => {
  const response = await fetch(`${API_BASE_URL}/${id}`, {
    method: "DELETE",
  });

  return response.text();
};
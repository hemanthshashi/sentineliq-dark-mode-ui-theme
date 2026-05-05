from flask import Flask, request, jsonify
from services.groq_client import GroqClient

app = Flask(__name__)

client = GroqClient()


# ✅ Health check route
@app.route("/", methods=["GET"])
def home():
    return {"message": "AI Service is running"}


# ✅ Main AI endpoint
@app.route("/generate", methods=["POST"])
def generate():
    data = request.get_json()

    prompt = data.get("prompt")

    if not prompt:
        return jsonify({"error": "Prompt is required"}), 400

    result = client.generate_response(prompt)

    return jsonify({"response": result})


# ✅ Run server
if __name__ == "__main__":
    app.run(debug=True)
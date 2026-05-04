print("🔥 FILE IS RUNNING")
from services.groq_client import GroqClient

client = GroqClient()

result = client.generate_response("Explain AI in simple words")

print(result)
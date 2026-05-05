from services.groq_client import GroqClient

client = GroqClient()

for i in range(7):
    print(f"\nRequest {i+1}:")
    print(client.generate_response("Explain AI simply"))
import os
import time
from groq import Groq
from dotenv import load_dotenv

load_dotenv(dotenv_path=".env")

class GroqClient:
    def __init__(self):
        self.client = Groq(api_key=os.getenv("GROQ_API_KEY"))

    def validate_input(self, prompt):
        if not prompt or not isinstance(prompt, str):
            return False, "Invalid input"

        if len(prompt) > 500:
            return False, "Input too long"

        return True, None

    def sanitize_input(self, prompt):
        blocked_words = [
            "ignore previous instructions",
            "act as admin",
            "bypass security",
            "system prompt",
            "jailbreak"
        ]

        lower_prompt = prompt.lower()

        for word in blocked_words:
            if word in lower_prompt:
                return None, "Malicious input detected"

        return prompt, None

    def generate_response(self, prompt):

        is_valid, error = self.validate_input(prompt)
        if not is_valid:
            return {"error": error}

        clean_prompt, error = self.sanitize_input(prompt)
        if error:
            return {"error": error}

        retries = 3

        for attempt in range(retries):
            try:
                response = self.client.chat.completions.create(
                    model="llama-3.3-70b-versatile",
                    messages=[
                        {"role": "user", "content": clean_prompt}
                    ],
                    temperature=0.7
                )

                return response.choices[0].message.content

            except Exception as e:
                print(f"⚠️ Attempt {attempt+1} failed:", e)
                time.sleep(2)

        return {
            "error": "AI service unavailable",
            "is_fallback": True
        }
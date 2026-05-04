import os
import time
from groq import Groq
from dotenv import load_dotenv

load_dotenv(dotenv_path=".env")


class GroqClient:
    def __init__(self):
        self.client = Groq(api_key=os.getenv("GROQ_API_KEY"))

        # ✅ Rate limit setup
        self.request_count = 0
        self.max_requests = 5
        self.reset_time = 60  # seconds
        self.start_time = time.time()

    # ✅ Input validation
    def validate_input(self, prompt):
        if not prompt or not isinstance(prompt, str):
            return False, "Invalid input"

        if len(prompt) > 500:
            return False, "Input too long"

        return True, None

    # ✅ Prompt security
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

    # ✅ Rate limiting function
    def check_rate_limit(self):
        current_time = time.time()

        # Reset counter after time window
        if current_time - self.start_time > self.reset_time:
            self.request_count = 0
            self.start_time = current_time

        if self.request_count >= self.max_requests:
            return False, "Rate limit exceeded. Try again later."

        self.request_count += 1
        return True, None

    # ✅ Main function
    def generate_response(self, prompt):

        # Step 1: Validate
        is_valid, error = self.validate_input(prompt)
        if not is_valid:
            return {"error": error}

        # Step 2: Sanitize
        clean_prompt, error = self.sanitize_input(prompt)
        if error:
            return {"error": error}

        # Step 3: Rate limit
        allowed, error = self.check_rate_limit()
        if not allowed:
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
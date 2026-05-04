def __init__(self):
    print("API KEY:", os.getenv("GROQ_API_KEY"))
    self.client = Groq(api_key=os.getenv("GROQ_API_KEY"))
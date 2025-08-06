# 15 requests per minute free
import google.generativeai as genai
genai.configure(api_key="YOUR_GEMINI_KEY")
model = genai.GenerativeModel('gemini-pro')
print(model)

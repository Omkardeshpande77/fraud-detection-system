from fastapi import FastAPI

from app.predictor import FraudPredictor
from app.schemas import PredictionRequest

app = FastAPI(
    title="ML Scoring Service",
    version="1.0.0"
)

predictor = FraudPredictor()


@app.get("/health")
def health():
    return {
        "status": "UP",
        "service": "ml-scoring-service"
    }


@app.post("/predict")
def predict(request: PredictionRequest):
    return predictor.predict(request)
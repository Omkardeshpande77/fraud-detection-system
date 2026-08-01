from app.schemas import PredictionRequest, PredictionResponse


class FraudPredictor:

    def predict(self, request: PredictionRequest) -> PredictionResponse:

        score = 0.0

        if request.amount > 100000:
            score += 40

        if request.country.lower() in [
            "nigeria",
            "russia",
            "north korea"
        ]:
            score += 30

        if request.isNewDevice:
            score += 20

        if request.transactionsLastHour > 5:
            score += 10

        probability = min(score / 100.0, 1.0)

        return PredictionResponse(
            riskScore=score,
            fraudProbability=probability,
            fraudulent=score >= 70
        )
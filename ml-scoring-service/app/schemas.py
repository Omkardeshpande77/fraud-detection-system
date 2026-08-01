from pydantic import BaseModel


class PredictionRequest(BaseModel):
    amount: float
    country: str
    merchantCategory: str
    paymentMethod: str
    isNewDevice: bool
    transactionsLastHour: int


class PredictionResponse(BaseModel):
    riskScore: float
    fraudProbability: float
    fraudulent: bool
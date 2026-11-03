# Credential Compliance Evaluator (serverless function)

Quarkus Funqy function on Knative (scale to zero). Compares missing requirements and completion percentage against credential targets; one finding per breach.

Invoke: `POST /credentialComplianceEvaluator` with the KPI payload.

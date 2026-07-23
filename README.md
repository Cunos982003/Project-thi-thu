Sơ đồ kiến trúc
```text
┌─────────────────────────────────────────────────────────┐
│                    API Gateway                          │
│              (Routes & Global Logging)                  │
└──────────────┬──────────────────────────────────────────┘
               │
        ┌──────┴────────┐
        │               │
    ┌────▼─────┐    ┌───▼──────┐
    │ Student  │    │  Course  │
    │ Service  │    │ Service  │
    └────┬─────┘    └────┬─────┘
         │               │
         └───────┬───────┘
                 │
           ┌─────▼──────┐
           │   Eureka   │
           │  (Registry)│
           └────────────┘

Communication Methods:
• Sync: OpenFeign
• Async: Kafka
```
### 1. Service Discovery Mechanism
The Gateway uses Eureka to discover services dynamically rather than hardcoding IP/Port:
- Services register themselves with Eureka
- Gateway queries Eureka for service locations
- Allows dynamic scaling without Gateway reconfiguration

### 2. Scaling Strategy
- Use `@EnableDiscoveryClient` on services
- Services auto-register; Gateway uses `lb://service-name` (load balanced)
- No Gateway config changes needed when scaling

### 3. OpenFeign vs Kafka
**OpenFeign (Sync)**:
- Pros: Simple, real-time validation, immediate response
- Cons: Tight coupling, failures block enrollment

**Kafka (Async)**:
- Pros: Decoupled, resilient, scalable
- Cons: Complex, eventual consistency

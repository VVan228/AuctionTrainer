# AuctionTrainer
Online auction app.

#### Run prod container
```
docker compose -f ./docker-compose.prod.yml up
```

#### All containers
- auc-server
- auc-client
- auc-centrifugo
- auc-db

#### TODO:
- [ ] add real channels to rooms
- [ ] new auction screen participants (full screen lot with timer)
- [ ] fix of owner auction screen
- [ ] ability to create rooms without templates
- [ ] images in lots?
- [ ] list of running rooms?
- [ ] development container

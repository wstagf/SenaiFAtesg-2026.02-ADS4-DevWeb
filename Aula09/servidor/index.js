const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express()
const port = 3000

app.use(bodyParser.json());
app.use(cors());

app.get('/health', (req, res) => {
  res.send('Server is running!')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

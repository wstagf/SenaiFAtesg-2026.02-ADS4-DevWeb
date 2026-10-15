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

app.post('/echo', (req, res) => {
    res.json(req.body);
});

app.use('/produtos', require('./routes/produtoRoutes'));

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

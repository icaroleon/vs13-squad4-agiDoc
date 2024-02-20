use vem_ser

db.createCollection("processes")

db.processes.insertMany([{
  "processNumber": UUID(),
  "title": "Nova Licitação",
  "description": "Construção de uma ponte na cidade de São Paulo",
  "status": "IN_PROGRESS",
}, 
{"processNumber": UUID(), "title": "Licitação de Obra Pública", "description": "Pavimentação da Estrada X na Cidade de Porto Alegre", "status": "IN_PROGRESS"},
{
  "processNumber": UUID(),
  "title": "Nova Licitação",
  "description": "Construção de uma ponte na cidade de São Paulo",
  "status": "IN_PROGRESS",
},

{
  "processNumber": UUID(),
  "title": "Processo de Compra",
  "description": "Aquisição de materiais de escritório",
  "status": "IN_PROGRESS"
}
]);

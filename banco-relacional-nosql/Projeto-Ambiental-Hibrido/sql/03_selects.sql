SELECT
    r.id_rio,
    r.nome,
    b.nome AS bioma,
    r.extensao_km
FROM Rio r
INNER JOIN Bioma b
    ON r.id_bioma = b.id_bioma
WHERE b.nome = 'Cerrado'
ORDER BY r.nome;


SELECT DISTINCT
    r.*,
    b.nome AS bioma,
    m.qualidade Qualidade
FROM Rio r
JOIN Bioma b
    ON r.id_bioma = b.id_bioma
JOIN Trecho_Rio t
    ON r.id_rio = t.id_rio
JOIN Ponto_Monitoramento p
    ON t.id_trecho = p.id_trecho
JOIN Monitoramento m
    ON p.id_ponto = m.id_ponto
WHERE m.qualidade = 'Boa';

SELECT
    t.id_trecho,
    t.cidade,
    t.estado,
    r.nome AS rio,
    e.nome_popular AS especie
FROM Trecho_Rio t
INNER JOIN Rio r
    ON t.id_rio = r.id_rio
INNER JOIN Trecho_Especie te
    ON t.id_trecho = te.id_trecho
INNER JOIN Especie e
    ON te.id_especie = e.id_especie
WHERE e.nome_popular = 'Espécie 1';

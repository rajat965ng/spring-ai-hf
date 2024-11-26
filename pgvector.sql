

--1536 is the default embedding dimension
--replace the 1536 with the actual embedding dimension if you are using a different dimension. PGvector supports at most 2000 dimensions for HNSW indexes.

CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS vector_store (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	content text,
	metadata json,
	embedding vector(384)
);

CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);
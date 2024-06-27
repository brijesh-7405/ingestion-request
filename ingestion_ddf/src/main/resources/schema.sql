CREATE SEQUENCE IF NOT EXISTS public.application_reference_table_reference_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.application_reference_table
(
    reference_id bigint NOT NULL DEFAULT nextval('application_reference_table_reference_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    reference_data character varying(255) COLLATE pg_catalog."default",
    reference_data_type character varying(255) COLLATE pg_catalog."default",
    reference_group_type character varying(255) COLLATE pg_catalog."default",
    reference_order bigint,
    CONSTRAINT application_reference_table_pkey PRIMARY KEY (reference_id)
);

CREATE SEQUENCE IF NOT EXISTS public.email_template_template_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.email_template
(
    template_id bigint NOT NULL DEFAULT nextval('email_template_template_id_seq'::regclass),
    body character varying(1024) COLLATE pg_catalog."default",
    subject character varying(255) COLLATE pg_catalog."default",
    template_code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT email_template_pkey PRIMARY KEY (template_id)
);

CREATE SEQUENCE IF NOT EXISTS public.status_status_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.status
(
    status_id bigint NOT NULL DEFAULT nextval('status_status_id_seq'::regclass),
    status_code character varying(255) COLLATE pg_catalog."default",
    status_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT status_pkey PRIMARY KEY (status_id)
);

CREATE SEQUENCE IF NOT EXISTS public.ingestion_request_details_ingestion_request_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.ingestion_request_details
(
    ingestion_request_id bigint NOT NULL DEFAULT nextval('ingestion_request_details_ingestion_request_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    modified_reason character varying(255) COLLATE pg_catalog."default",
    request_rationale_reason character varying(255) COLLATE pg_catalog."default",
    requested_by_email character varying(255) COLLATE pg_catalog."default",
    requested_by_mudid character varying(255) COLLATE pg_catalog."default",
    requested_by_name character varying(255) COLLATE pg_catalog."default",
    requester_email character varying(255) COLLATE pg_catalog."default",
    requester_mudid character varying(255) COLLATE pg_catalog."default",
    requester_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT ingestion_request_details_pkey PRIMARY KEY (ingestion_request_id)
);

CREATE SEQUENCE IF NOT EXISTS public.validation_notes_notes_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.validation_notes
(
    notes_id bigint NOT NULL DEFAULT nextval('validation_notes_notes_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    notes character varying(255) COLLATE pg_catalog."default",
    ingestion_request_id bigint,
    CONSTRAINT validation_notes_pkey PRIMARY KEY (notes_id),
    CONSTRAINT fkqwwo1sjvpuollp7wa8luie4ed FOREIGN KEY (ingestion_request_id)
    REFERENCES public.ingestion_request_details (ingestion_request_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.technical_details_technical_request_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.technical_details
(
    technical_request_id bigint NOT NULL DEFAULT nextval('technical_details_technical_request_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    data_location_path character varying(255) COLLATE pg_catalog."default",
    data_refresh_frequency character varying(255) COLLATE pg_catalog."default",
    domain_request_id character varying(255) COLLATE pg_catalog."default",
    existing_data_location_identified character varying(255) COLLATE pg_catalog."default",
    external_data_source_location character varying(255) COLLATE pg_catalog."default",
    external_source_secret_key_name character varying(255) COLLATE pg_catalog."default",
    external_staging_container_name character varying(255) COLLATE pg_catalog."default",
    gsk_access_source_location_ref character varying(255) COLLATE pg_catalog."default",
    guest_users_email character varying(255) COLLATE pg_catalog."default",
    target_ingestion_end_date timestamp without time zone,
    target_ingestion_start_date timestamp without time zone,
    target_path character varying(255) COLLATE pg_catalog."default",
    whitelist_ip_addresses character varying(255) COLLATE pg_catalog."default",
    ingestion_request_id bigint,
    dataset_type_ingestion_ref character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT technical_details_pkey PRIMARY KEY (technical_request_id),
    CONSTRAINT fk24ctov1bpsy0vwr4g6pdnjntd FOREIGN KEY (ingestion_request_id)
    REFERENCES public.ingestion_request_details (ingestion_request_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_details_dataset_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_details
(
    dataset_id bigint NOT NULL DEFAULT nextval('dataset_details_dataset_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    analysis_end_dt timestamp without time zone,
    analysis_init_dt timestamp without time zone,
    contract_partner character varying(255) COLLATE pg_catalog."default",
    current_data_location_ref character varying(255) COLLATE pg_catalog."default",
    dataset_name character varying(255) COLLATE pg_catalog."default",
    dataset_origin_source character varying(255) COLLATE pg_catalog."default",
    dataset_required_for_ref character varying(255) COLLATE pg_catalog."default",
    dataset_type_ref character varying(255) COLLATE pg_catalog."default",
    dta_contract_complete_flag bool,
    dta_expected_completion_date timestamp without time zone,
    estimated_data_volume_ref character varying(255) COLLATE pg_catalog."default",
    ihd_flag bool,
    information_classification_type_ref character varying(255) COLLATE pg_catalog."default",
    meteor_space_domino_usage_flag bool,
    pii_type_ref character varying(255) COLLATE pg_catalog."default",
    retention_rules character varying(255) COLLATE pg_catalog."default",
    retention_rules_contract_date timestamp without time zone,
    ingestion_request_id bigint,
    CONSTRAINT dataset_details_pkey PRIMARY KEY (dataset_id),
    CONSTRAINT fk1j63jpo6jquwl47sjamt9rlrr FOREIGN KEY (ingestion_request_id)
    REFERENCES public.ingestion_request_details (ingestion_request_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_data_category_dataset_data_category_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_data_category
(
    dataset_data_category_id bigint NOT NULL DEFAULT nextval('dataset_data_category_dataset_data_category_id_seq'::regclass),
    data_category_ref character varying(255) COLLATE pg_catalog."default",
    dataset_id bigint,
    CONSTRAINT dataset_data_category_pkey PRIMARY KEY (dataset_data_category_id),
    CONSTRAINT fkfmr6f6exfdx5acup66c7u2cw4 FOREIGN KEY (dataset_id)
    REFERENCES public.dataset_details (dataset_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_indication_dataset_indication_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_indication
(
    dataset_indication_id bigint NOT NULL DEFAULT nextval('dataset_indication_dataset_indication_id_seq'::regclass),
    indication character varying(255) COLLATE pg_catalog."default",
    dataset_id bigint,
    CONSTRAINT dataset_indication_pkey PRIMARY KEY (dataset_indication_id),
    CONSTRAINT fki0xiicie0ic1han5y8bmhqdf FOREIGN KEY (dataset_id)
    REFERENCES public.dataset_details (dataset_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.request_status_details_request_status_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.request_status_details
(
    request_status_id bigint NOT NULL DEFAULT nextval('request_status_details_request_status_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    active_flag bool,
    decision_by_email character varying(255) COLLATE pg_catalog."default",
    decision_by_mudid character varying(255) COLLATE pg_catalog."default",
    decision_by_name character varying(255) COLLATE pg_catalog."default",
    decision_comments character varying(255) COLLATE pg_catalog."default",
    decision_date timestamp without time zone,
    rejection_reason character varying(255) COLLATE pg_catalog."default",
    ingestion_request_id bigint,
    status_id bigint,
    CONSTRAINT request_status_details_pkey PRIMARY KEY (request_status_id),
    CONSTRAINT fk2b5o26hmrmmoxs8a0dwnmf0wj FOREIGN KEY (status_id)
    REFERENCES public.status (status_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION,
    CONSTRAINT fk79m81a7kt01xgfkrigbnsetpm FOREIGN KEY (ingestion_request_id)
    REFERENCES public.ingestion_request_details (ingestion_request_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_role_details_dataset_role_details_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_role_details
(
    dataset_role_details_id bigint NOT NULL  DEFAULT nextval('dataset_role_details_dataset_role_details_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    mudid character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    dataset_id bigint,
    CONSTRAINT dataset_role_details_pkey PRIMARY KEY (dataset_role_details_id),
    CONSTRAINT fksdv5roo52oavb9l71gaj61nrx FOREIGN KEY (dataset_id)
    REFERENCES public.dataset_details (dataset_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_study_dataset_study_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_study
(
    dataset_study_id bigint NOT NULL DEFAULT nextval('dataset_study_dataset_study_id_seq'::regclass),
    study_id character varying(255) COLLATE pg_catalog."default",
    dataset_id bigint,
    CONSTRAINT dataset_study_pkey PRIMARY KEY (dataset_study_id),
    CONSTRAINT fktduwug5h2k0sgvo359c8rbg48 FOREIGN KEY (dataset_id)
    REFERENCES public.dataset_details (dataset_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_tech_and_assay_dataset_tech_and_assay_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_tech_and_assay
(
    dataset_tech_and_assay_id bigint NOT NULL  DEFAULT nextval('dataset_tech_and_assay_dataset_tech_and_assay_id_seq'::regclass),
    technique_and_assay character varying(255) COLLATE pg_catalog."default",
    dataset_id bigint,
    CONSTRAINT dataset_tech_and_assay_pkey PRIMARY KEY (dataset_tech_and_assay_id),
    CONSTRAINT fkbbtc8v7op5p5h4ns4jniegfx5 FOREIGN KEY (dataset_id)
    REFERENCES public.dataset_details (dataset_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_therapy_dataset_therapy_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_therapy
(
    dataset_therapy_id bigint NOT NULL DEFAULT nextval('dataset_therapy_dataset_therapy_id_seq'::regclass),
    therapy character varying(255) COLLATE pg_catalog."default",
    dataset_id bigint,
    CONSTRAINT dataset_therapy_pkey PRIMARY KEY (dataset_therapy_id),
    CONSTRAINT fk9o5ax4bpqn3xhitjhkr3gr06n FOREIGN KEY (dataset_id)
    REFERENCES public.dataset_details (dataset_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.dataset_user_usage_restriction_usage_user_restriction_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE IF NOT EXISTS public.dataset_user_usage_restriction
(
    usage_user_restriction_id bigint NOT NULL DEFAULT nextval('dataset_user_usage_restriction_usage_user_restriction_id_seq'::regclass),
    created_by character varying(255) COLLATE pg_catalog."default",
    created_date timestamp without time zone,
    modified_by character varying(255) COLLATE pg_catalog."default",
    modified_date timestamp without time zone,
    reason_for_other character varying(255) COLLATE pg_catalog."default",
    restriction_ref character varying(255) COLLATE pg_catalog."default",
    restriction_type_ref character varying(255) COLLATE pg_catalog."default",
    dataset_id bigint,
    CONSTRAINT dataset_user_usage_restriction_pkey PRIMARY KEY (usage_user_restriction_id),
    CONSTRAINT fkfocbuueohuv0c207f73s0sxxa FOREIGN KEY (dataset_id)
    REFERENCES public.dataset_details (dataset_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION
);


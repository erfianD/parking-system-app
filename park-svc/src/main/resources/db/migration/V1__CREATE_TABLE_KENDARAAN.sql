CREATE TABLE vehicle (
	plat_nomor varchar(255) NULL,
	jenis_kendaraan varchar(255) NULL,
	tarif_parkir int4 NULL,
	jam_masuk timestamp NULL,
	jam_keluar timestamp NULL,
	id uuid NOT NULL,
	total_waktu int4 NULL,
	total_tarif int4 NULL,
	status varchar(255) NULL
);
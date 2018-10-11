PGDMP                     	    v           biosip    10.5    10.5 X    x           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            y           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            z           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            {           1262    24789    biosip    DATABASE     �   CREATE DATABASE biosip WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE biosip;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            |           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            }           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24907    categoria_seq    SEQUENCE     v   CREATE SEQUENCE public.categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.categoria_seq;
       public       postgres    false    3            �            1259    24790 	   categoria    TABLE     �   CREATE TABLE public.categoria (
    id integer DEFAULT nextval('public.categoria_seq'::regclass) NOT NULL,
    nombre character varying(45) NOT NULL,
    descripcion character varying(500) NOT NULL
);
    DROP TABLE public.categoria;
       public         postgres    false    209    3            �            1259    24815    confirmacion    TABLE     �   CREATE TABLE public.confirmacion (
    token character(100) NOT NULL,
    usuario_id bigint NOT NULL,
    fecha_de_alta timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
     DROP TABLE public.confirmacion;
       public         postgres    false    3            �            1259    24818    kit    TABLE     �   CREATE TABLE public.kit (
    id bigint NOT NULL,
    usuario_id_autor bigint NOT NULL,
    fecha_de_expiracion timestamp without time zone NOT NULL,
    administrador_id_aprobador bigint
);
    DROP TABLE public.kit;
       public         postgres    false    3            �            1259    24827    kit_material    TABLE     �   CREATE TABLE public.kit_material (
    kit_id bigint NOT NULL,
    material_id bigint NOT NULL,
    num_elementos_requeridos integer NOT NULL
);
     DROP TABLE public.kit_material;
       public         postgres    false    3            �            1259    24913    material_seq    SEQUENCE     u   CREATE SEQUENCE public.material_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.material_seq;
       public       postgres    false    3            �            1259    24821    material    TABLE       CREATE TABLE public.material (
    id bigint DEFAULT nextval('public.material_seq'::regclass) NOT NULL,
    nombre character varying(200) NOT NULL,
    disponibles integer NOT NULL,
    ruta_imagen character varying(100),
    descripcion character varying(500) NOT NULL
);
    DROP TABLE public.material;
       public         postgres    false    211    3            �            1259    24830    material_categoria    TABLE     o   CREATE TABLE public.material_categoria (
    material_id bigint NOT NULL,
    categoria_id integer NOT NULL
);
 &   DROP TABLE public.material_categoria;
       public         postgres    false    3            �            1259    24839    material_subcategoria    TABLE     u   CREATE TABLE public.material_subcategoria (
    material_id bigint NOT NULL,
    subcategoria_id integer NOT NULL
);
 )   DROP TABLE public.material_subcategoria;
       public         postgres    false    3            �            1259    24919 
   perfil_seq    SEQUENCE     s   CREATE SEQUENCE public.perfil_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.perfil_seq;
       public       postgres    false    3            �            1259    24842    perfil    TABLE     �   CREATE TABLE public.perfil (
    id smallint DEFAULT nextval('public.perfil_seq'::regclass) NOT NULL,
    nombre character varying(45) NOT NULL,
    descripcion character varying(200) NOT NULL
);
    DROP TABLE public.perfil;
       public         postgres    false    213    3            �            1259    24857    prestamo    TABLE     A  CREATE TABLE public.prestamo (
    id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    fecha_de_solicitud timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_de_aprobacion timestamp without time zone,
    administrador_id_aprobador bigint,
    fecha_de_devolucion timestamp without time zone
);
    DROP TABLE public.prestamo;
       public         postgres    false    3            �            1259    24860    prestamo_material    TABLE     �   CREATE TABLE public.prestamo_material (
    prestamo_id bigint NOT NULL,
    material_id bigint NOT NULL,
    elementos_prestados integer NOT NULL
);
 %   DROP TABLE public.prestamo_material;
       public         postgres    false    3            �            1259    24916    subcategoria_seq    SEQUENCE     y   CREATE SEQUENCE public.subcategoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.subcategoria_seq;
       public       postgres    false    3            �            1259    24833    subcategoria    TABLE     �   CREATE TABLE public.subcategoria (
    id integer DEFAULT nextval('public.subcategoria_seq'::regclass) NOT NULL,
    nombre character varying(45) NOT NULL,
    descripcion character varying(500) NOT NULL,
    categoria_id integer
);
     DROP TABLE public.subcategoria;
       public         postgres    false    212    3            �            1259    24910    usuario_seq    SEQUENCE     t   CREATE SEQUENCE public.usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.usuario_seq;
       public       postgres    false    3            �            1259    24796    usuario    TABLE     �  CREATE TABLE public.usuario (
    id bigint DEFAULT nextval('public.usuario_seq'::regclass) NOT NULL,
    nombre_completo character varying(210) NOT NULL,
    user_name character varying(50) NOT NULL,
    correo_ciencias character varying(150) NOT NULL,
    password character varying(100) NOT NULL,
    fecha_de_desbloqueo timestamp without time zone,
    ruta_imagen character varying(100),
    validado boolean DEFAULT false NOT NULL
);
    DROP TABLE public.usuario;
       public         postgres    false    210    3            �            1259    24863    usuario_perfil    TABLE     h   CREATE TABLE public.usuario_perfil (
    usuario_id bigint NOT NULL,
    perfil_id smallint NOT NULL
);
 "   DROP TABLE public.usuario_perfil;
       public         postgres    false    3            d          0    24790 	   categoria 
   TABLE DATA               <   COPY public.categoria (id, nombre, descripcion) FROM stdin;
    public       postgres    false    196   e       f          0    24815    confirmacion 
   TABLE DATA               H   COPY public.confirmacion (token, usuario_id, fecha_de_alta) FROM stdin;
    public       postgres    false    198   <e       g          0    24818    kit 
   TABLE DATA               d   COPY public.kit (id, usuario_id_autor, fecha_de_expiracion, administrador_id_aprobador) FROM stdin;
    public       postgres    false    199   Ye       i          0    24827    kit_material 
   TABLE DATA               U   COPY public.kit_material (kit_id, material_id, num_elementos_requeridos) FROM stdin;
    public       postgres    false    201   ve       h          0    24821    material 
   TABLE DATA               U   COPY public.material (id, nombre, disponibles, ruta_imagen, descripcion) FROM stdin;
    public       postgres    false    200   �e       j          0    24830    material_categoria 
   TABLE DATA               G   COPY public.material_categoria (material_id, categoria_id) FROM stdin;
    public       postgres    false    202   �e       l          0    24839    material_subcategoria 
   TABLE DATA               M   COPY public.material_subcategoria (material_id, subcategoria_id) FROM stdin;
    public       postgres    false    204   �e       m          0    24842    perfil 
   TABLE DATA               9   COPY public.perfil (id, nombre, descripcion) FROM stdin;
    public       postgres    false    205   �e       n          0    24857    prestamo 
   TABLE DATA               �   COPY public.prestamo (id, usuario_id, fecha_de_solicitud, fecha_de_aprobacion, administrador_id_aprobador, fecha_de_devolucion) FROM stdin;
    public       postgres    false    206   f       o          0    24860    prestamo_material 
   TABLE DATA               Z   COPY public.prestamo_material (prestamo_id, material_id, elementos_prestados) FROM stdin;
    public       postgres    false    207   $f       k          0    24833    subcategoria 
   TABLE DATA               M   COPY public.subcategoria (id, nombre, descripcion, categoria_id) FROM stdin;
    public       postgres    false    203   Af       e          0    24796    usuario 
   TABLE DATA               �   COPY public.usuario (id, nombre_completo, user_name, correo_ciencias, password, fecha_de_desbloqueo, ruta_imagen, validado) FROM stdin;
    public       postgres    false    197   ^f       p          0    24863    usuario_perfil 
   TABLE DATA               ?   COPY public.usuario_perfil (usuario_id, perfil_id) FROM stdin;
    public       postgres    false    208   {f       ~           0    0    categoria_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.categoria_seq', 1, false);
            public       postgres    false    209                       0    0    material_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.material_seq', 1, false);
            public       postgres    false    211            �           0    0 
   perfil_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.perfil_seq', 1, false);
            public       postgres    false    213            �           0    0    subcategoria_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.subcategoria_seq', 1, false);
            public       postgres    false    212            �           0    0    usuario_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.usuario_seq', 1, false);
            public       postgres    false    210            �
           2606    24867    categoria primary 
   CONSTRAINT     Q   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT "primary" PRIMARY KEY (id);
 =   ALTER TABLE ONLY public.categoria DROP CONSTRAINT "primary";
       public         postgres    false    196            �
           2606    24870    usuario primary1 
   CONSTRAINT     N   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT primary1 PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.usuario DROP CONSTRAINT primary1;
       public         postgres    false    197            �
           2606    24898    prestamo primary10 
   CONSTRAINT     P   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT primary10 PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT primary10;
       public         postgres    false    206            �
           2606    24902    prestamo_material primary11 
   CONSTRAINT     o   ALTER TABLE ONLY public.prestamo_material
    ADD CONSTRAINT primary11 PRIMARY KEY (prestamo_id, material_id);
 E   ALTER TABLE ONLY public.prestamo_material DROP CONSTRAINT primary11;
       public         postgres    false    207    207            �
           2606    24905    usuario_perfil primary12 
   CONSTRAINT     i   ALTER TABLE ONLY public.usuario_perfil
    ADD CONSTRAINT primary12 PRIMARY KEY (usuario_id, perfil_id);
 B   ALTER TABLE ONLY public.usuario_perfil DROP CONSTRAINT primary12;
       public         postgres    false    208    208            �
           2606    24874    confirmacion primary2 
   CONSTRAINT     V   ALTER TABLE ONLY public.confirmacion
    ADD CONSTRAINT primary2 PRIMARY KEY (token);
 ?   ALTER TABLE ONLY public.confirmacion DROP CONSTRAINT primary2;
       public         postgres    false    198            �
           2606    24877    kit primary3 
   CONSTRAINT     J   ALTER TABLE ONLY public.kit
    ADD CONSTRAINT primary3 PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.kit DROP CONSTRAINT primary3;
       public         postgres    false    199            �
           2606    24881    material primary4 
   CONSTRAINT     O   ALTER TABLE ONLY public.material
    ADD CONSTRAINT primary4 PRIMARY KEY (id);
 ;   ALTER TABLE ONLY public.material DROP CONSTRAINT primary4;
       public         postgres    false    200            �
           2606    24883    kit_material primary5 
   CONSTRAINT     d   ALTER TABLE ONLY public.kit_material
    ADD CONSTRAINT primary5 PRIMARY KEY (kit_id, material_id);
 ?   ALTER TABLE ONLY public.kit_material DROP CONSTRAINT primary5;
       public         postgres    false    201    201            �
           2606    24886    material_categoria primary6 
   CONSTRAINT     p   ALTER TABLE ONLY public.material_categoria
    ADD CONSTRAINT primary6 PRIMARY KEY (material_id, categoria_id);
 E   ALTER TABLE ONLY public.material_categoria DROP CONSTRAINT primary6;
       public         postgres    false    202    202            �
           2606    24889    subcategoria primary7 
   CONSTRAINT     S   ALTER TABLE ONLY public.subcategoria
    ADD CONSTRAINT primary7 PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.subcategoria DROP CONSTRAINT primary7;
       public         postgres    false    203            �
           2606    24893    material_subcategoria primary8 
   CONSTRAINT     v   ALTER TABLE ONLY public.material_subcategoria
    ADD CONSTRAINT primary8 PRIMARY KEY (material_id, subcategoria_id);
 H   ALTER TABLE ONLY public.material_subcategoria DROP CONSTRAINT primary8;
       public         postgres    false    204    204            �
           2606    24896    perfil primary9 
   CONSTRAINT     M   ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT primary9 PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.perfil DROP CONSTRAINT primary9;
       public         postgres    false    205            �
           1259    24871    correo_ciencias_unique    INDEX     \   CREATE UNIQUE INDEX correo_ciencias_unique ON public.usuario USING btree (correo_ciencias);
 *   DROP INDEX public.correo_ciencias_unique;
       public         postgres    false    197            �
           1259    24891    fk_categoria_idx    INDEX     Q   CREATE INDEX fk_categoria_idx ON public.subcategoria USING btree (categoria_id);
 $   DROP INDEX public.fk_categoria_idx;
       public         postgres    false    203            �
           1259    24887    fk_item_categoria_2_idx    INDEX     ^   CREATE INDEX fk_item_categoria_2_idx ON public.material_categoria USING btree (categoria_id);
 +   DROP INDEX public.fk_item_categoria_2_idx;
       public         postgres    false    202            �
           1259    24894    fk_item_subcategoria_2_idx    INDEX     g   CREATE INDEX fk_item_subcategoria_2_idx ON public.material_subcategoria USING btree (subcategoria_id);
 .   DROP INDEX public.fk_item_subcategoria_2_idx;
       public         postgres    false    204            �
           1259    24878    fk_kit_1_idx    INDEX     H   CREATE INDEX fk_kit_1_idx ON public.kit USING btree (usuario_id_autor);
     DROP INDEX public.fk_kit_1_idx;
       public         postgres    false    199            �
           1259    24879    fk_kit_2_idx    INDEX     R   CREATE INDEX fk_kit_2_idx ON public.kit USING btree (administrador_id_aprobador);
     DROP INDEX public.fk_kit_2_idx;
       public         postgres    false    199            �
           1259    24884    fk_kit_item_2_idx    INDEX     Q   CREATE INDEX fk_kit_item_2_idx ON public.kit_material USING btree (material_id);
 %   DROP INDEX public.fk_kit_item_2_idx;
       public         postgres    false    201            �
           1259    24899    fk_prestamo_1_idx    INDEX     L   CREATE INDEX fk_prestamo_1_idx ON public.prestamo USING btree (usuario_id);
 %   DROP INDEX public.fk_prestamo_1_idx;
       public         postgres    false    206            �
           1259    24900    fk_prestamo_2_idx    INDEX     \   CREATE INDEX fk_prestamo_2_idx ON public.prestamo USING btree (administrador_id_aprobador);
 %   DROP INDEX public.fk_prestamo_2_idx;
       public         postgres    false    206            �
           1259    24903    fk_prestamo_item_2_idx    INDEX     [   CREATE INDEX fk_prestamo_item_2_idx ON public.prestamo_material USING btree (material_id);
 *   DROP INDEX public.fk_prestamo_item_2_idx;
       public         postgres    false    207            �
           1259    24875    fk_usuario_conf_idx    INDEX     R   CREATE INDEX fk_usuario_conf_idx ON public.confirmacion USING btree (usuario_id);
 '   DROP INDEX public.fk_usuario_conf_idx;
       public         postgres    false    198            �
           1259    24906    fk_usuario_perfil_1_idx    INDEX     W   CREATE INDEX fk_usuario_perfil_1_idx ON public.usuario_perfil USING btree (perfil_id);
 +   DROP INDEX public.fk_usuario_perfil_1_idx;
       public         postgres    false    208            �
           1259    24868    nombre_unique    INDEX     L   CREATE UNIQUE INDEX nombre_unique ON public.categoria USING btree (nombre);
 !   DROP INDEX public.nombre_unique;
       public         postgres    false    196            �
           1259    24890    nombre_unique1    INDEX     P   CREATE UNIQUE INDEX nombre_unique1 ON public.subcategoria USING btree (nombre);
 "   DROP INDEX public.nombre_unique1;
       public         postgres    false    203            �
           1259    24872    user_name_unique    INDEX     P   CREATE UNIQUE INDEX user_name_unique ON public.usuario USING btree (user_name);
 $   DROP INDEX public.user_name_unique;
       public         postgres    false    197            �
           2606    24957    subcategoria fk_categoria    FK CONSTRAINT     �   ALTER TABLE ONLY public.subcategoria
    ADD CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES public.categoria(id);
 C   ALTER TABLE ONLY public.subcategoria DROP CONSTRAINT fk_categoria;
       public       postgres    false    203    196    2740            �
           2606    24947 &   material_categoria fk_item_categoria_1    FK CONSTRAINT     �   ALTER TABLE ONLY public.material_categoria
    ADD CONSTRAINT fk_item_categoria_1 FOREIGN KEY (material_id) REFERENCES public.material(id);
 P   ALTER TABLE ONLY public.material_categoria DROP CONSTRAINT fk_item_categoria_1;
       public       postgres    false    202    200    2753            �
           2606    24952 &   material_categoria fk_item_categoria_2    FK CONSTRAINT     �   ALTER TABLE ONLY public.material_categoria
    ADD CONSTRAINT fk_item_categoria_2 FOREIGN KEY (categoria_id) REFERENCES public.categoria(id);
 P   ALTER TABLE ONLY public.material_categoria DROP CONSTRAINT fk_item_categoria_2;
       public       postgres    false    196    202    2740            �
           2606    24962 ,   material_subcategoria fk_item_subcategoria_1    FK CONSTRAINT     �   ALTER TABLE ONLY public.material_subcategoria
    ADD CONSTRAINT fk_item_subcategoria_1 FOREIGN KEY (material_id) REFERENCES public.material(id);
 V   ALTER TABLE ONLY public.material_subcategoria DROP CONSTRAINT fk_item_subcategoria_1;
       public       postgres    false    200    204    2753            �
           2606    24967 ,   material_subcategoria fk_item_subcategoria_2    FK CONSTRAINT     �   ALTER TABLE ONLY public.material_subcategoria
    ADD CONSTRAINT fk_item_subcategoria_2 FOREIGN KEY (subcategoria_id) REFERENCES public.subcategoria(id);
 V   ALTER TABLE ONLY public.material_subcategoria DROP CONSTRAINT fk_item_subcategoria_2;
       public       postgres    false    203    204    2763            �
           2606    24927    kit fk_kit_1    FK CONSTRAINT     v   ALTER TABLE ONLY public.kit
    ADD CONSTRAINT fk_kit_1 FOREIGN KEY (usuario_id_autor) REFERENCES public.usuario(id);
 6   ALTER TABLE ONLY public.kit DROP CONSTRAINT fk_kit_1;
       public       postgres    false    197    2743    199            �
           2606    24932    kit fk_kit_2    FK CONSTRAINT     �   ALTER TABLE ONLY public.kit
    ADD CONSTRAINT fk_kit_2 FOREIGN KEY (administrador_id_aprobador) REFERENCES public.usuario(id);
 6   ALTER TABLE ONLY public.kit DROP CONSTRAINT fk_kit_2;
       public       postgres    false    199    197    2743            �
           2606    24937    kit_material fk_kit_item_1    FK CONSTRAINT     v   ALTER TABLE ONLY public.kit_material
    ADD CONSTRAINT fk_kit_item_1 FOREIGN KEY (kit_id) REFERENCES public.kit(id);
 D   ALTER TABLE ONLY public.kit_material DROP CONSTRAINT fk_kit_item_1;
       public       postgres    false    201    2751    199            �
           2606    24942    kit_material fk_kit_item_2    FK CONSTRAINT     �   ALTER TABLE ONLY public.kit_material
    ADD CONSTRAINT fk_kit_item_2 FOREIGN KEY (material_id) REFERENCES public.material(id);
 D   ALTER TABLE ONLY public.kit_material DROP CONSTRAINT fk_kit_item_2;
       public       postgres    false    201    2753    200            �
           2606    24972    prestamo fk_prestamo_1    FK CONSTRAINT     z   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fk_prestamo_1 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);
 @   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT fk_prestamo_1;
       public       postgres    false    197    206    2743            �
           2606    24977    prestamo fk_prestamo_2    FK CONSTRAINT     �   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fk_prestamo_2 FOREIGN KEY (administrador_id_aprobador) REFERENCES public.usuario(id);
 @   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT fk_prestamo_2;
       public       postgres    false    197    2743    206            �
           2606    24982 $   prestamo_material fk_prestamo_item_1    FK CONSTRAINT     �   ALTER TABLE ONLY public.prestamo_material
    ADD CONSTRAINT fk_prestamo_item_1 FOREIGN KEY (prestamo_id) REFERENCES public.prestamo(id);
 N   ALTER TABLE ONLY public.prestamo_material DROP CONSTRAINT fk_prestamo_item_1;
       public       postgres    false    207    2772    206            �
           2606    24987 $   prestamo_material fk_prestamo_item_2    FK CONSTRAINT     �   ALTER TABLE ONLY public.prestamo_material
    ADD CONSTRAINT fk_prestamo_item_2 FOREIGN KEY (material_id) REFERENCES public.material(id);
 N   ALTER TABLE ONLY public.prestamo_material DROP CONSTRAINT fk_prestamo_item_2;
       public       postgres    false    207    2753    200            �
           2606    24922    confirmacion fk_usuario_conf    FK CONSTRAINT     �   ALTER TABLE ONLY public.confirmacion
    ADD CONSTRAINT fk_usuario_conf FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);
 F   ALTER TABLE ONLY public.confirmacion DROP CONSTRAINT fk_usuario_conf;
       public       postgres    false    2743    197    198            �
           2606    24992 "   usuario_perfil fk_usuario_perfil_1    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_perfil
    ADD CONSTRAINT fk_usuario_perfil_1 FOREIGN KEY (perfil_id) REFERENCES public.perfil(id);
 L   ALTER TABLE ONLY public.usuario_perfil DROP CONSTRAINT fk_usuario_perfil_1;
       public       postgres    false    205    2768    208            �
           2606    24997 "   usuario_perfil fk_usuario_perfil_2    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_perfil
    ADD CONSTRAINT fk_usuario_perfil_2 FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);
 L   ALTER TABLE ONLY public.usuario_perfil DROP CONSTRAINT fk_usuario_perfil_2;
       public       postgres    false    208    197    2743            d      x������ � �      f      x������ � �      g      x������ � �      i      x������ � �      h      x������ � �      j      x������ � �      l      x������ � �      m      x������ � �      n      x������ � �      o      x������ � �      k      x������ � �      e      x������ � �      p      x������ � �     
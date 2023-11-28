--ProgramaAcademico todo temrnar de ahcer lo demas
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('1','sistemas','PREGRADO', 'Ingeniería elelectrónica y telecomunicaciones');
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('2','electronica','PREGRADO', 'Ingeniería elelectrónica y telecomunicaciones');
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('3','civil','PREGRADO', 'Ingeniería Civil');

--RegistroCalificado
--todo verificar como nos deben llegar los colaboradores y autores
INSERT INTO `registro_calificado` (registro_calificado_id,autor,colaboradores,estado,fecha_creacion,programa_id) VALUES ('1','Deibar René Hurtado Herrera','Aida Patricia González Nieva, Jorge Enrique Barrera Moreno, Francisco José Pino Correa, César Alfaro Mosquera Dorado, Laura Ismenia Castellanos Vivas','PorAprobar','2023-10-11 19:33:42','1');

--Item
--todo validar el tema de la guia por cada item y subitem
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('1','null','null','Denominación del Programa','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('2','null','null','Justificación del Programa','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('3','null','null','Aspectos Curriculares','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('4','null','null','Organización de actividades académicas y proceso formativo','1');

INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('5','null','null','Investigación, innovación y/o creación artística y cultural','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('6','null','null','Relación con el sector externo','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('7','null','null','Profesores','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('8','null','null','Medios Educativos','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('9','null','null','Infraestructura física y tecnológica','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('10','null','null','Bibliografía','1');

--SubItem
--1
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('1','null','null','Información básica del programa','1',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('2','null','null','Análisis de la coherencia entre la denominación, el título a otorgar, el nivel de formación, los contenidos curriculares y el perfil de egreso','1',null);
--2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('3','null','null','Estado de la oferta de educación en el área del programa a nivel nacional y global','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('4','null','null','Necesidades de la región y del país','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('5','null','null','Justificación de atributos o factores que constituyen los rasgos distintivos del programa','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('6','null','null','Análisis por periodos académicos de indicadores para los programas similares de referencia y las acciones adoptadas por la institución frente a los mismos','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('7','null','null','Pertinencia del programa académico frente al desarrollo, social, cultural, ambiental, económico y científico','2',null);
--2.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('8','null','null','Personas inscritas, admitidas y matriculadas','2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('9','null','null','Total de matriculados y graduados','2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('10','null','null','Tasas de deserción por cohorte','2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('11','null','null','Empleabilidad de los egresados','2','6');
--3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('12','null','null','Componentes Formativos','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('13','null','null','Componentes pedagógicos','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('14','null','null','Componentes de interacción','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('15','null','null','Conceptualización teórica y epistemológica del programa','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('16','null','null',' Mecanismos de evaluación','3',null);
--3.1
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('17','null','null','Plan general de estudios representado en Créditos Académicos','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('18','null','null','Resultados de aprendizaje del programa','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('19','null','null','Formación integral','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('20','null','null','Estrategias de flexibilización curricular','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('21','null','null','Perfil de egreso','3','12');
--3.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('22','null','null','Modelo pedagógico y didáctico del programa','3','13');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('23','null','null','Innovación pedagógica y didáctica del programa','3','13');
--3.3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('24','null','null','Articulación con los contextos locales, regionales y globales','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('25','null','null','Desarrollo de habilidades en estudiantes y profesores para la interacción nacional e internacional','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('26','null','null','Internacionalización del currículo','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('27','null','null','Competencias comunicativas en una segunda lengua','3','14');
--3.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('27','null','null','Fundamentos teóricos y conceptuales del programa','3','15');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('28','null','null','Objeto de estudio y formas de conocimiento del programa','3','15');
--3.5
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('29','null','null','Instrumentos de medición y seguimiento del desempeño de profesores','3','16');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('30','null','null','Instrumentos de medición y seguimiento del desempeño de estudiantes','3','16');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('31','null','null','Seguimiento y evaluación de resultados de aprendizaje','3','16');

ISRT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('32','null','null','Diseño y contenido curricular','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('33','null','null','Créditos por semestres','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('34','null','null','Proyecto educativo del programa','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('35','null','null','Mecanismos de interacción entre estudiante-profesor y estudiante-estudiante','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('36','null','null','Actividades académicas apoyadas en TIC','4',null);
--4.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('37','null','null','Número de créditos obligatorios','4','33');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('38','null','null','Número de créditos electivos','4','33');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('39','null','null','Declaración para el programa académico, de la incorporación de la investigación, innovación y/o creación artística y cultural para el desarrollo del conocimiento, según el nivel de formación del programa y la tipología y misión institucional','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('40','null','null','Formación en investigación','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('41','null','null','Organización de la investigación, innovación y/o creación artística y cultural en el programa','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('42','null','null','Resultados de la investigación, innovación y/o creación artística y cultural','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('43','null','null','Políticas Institucionales','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('44','null','null','Objetivos del Sistema de Investigaciones','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('45','null','null','Políticas del Sistema de Investigaciones','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('46','null','null','Estímulos para la Investigación','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('47','null','null','La incorporación de las TIC en la formación investigativa','5',null);
--5.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('48','null','null','Estrategias para la formación en investigación-creación-innovación','5','40');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('49','null','null','Desarrollo del pensamiento crítico y/o creativo','5','40');
--5.3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('50','null','null','Áreas, líneas y/o temáticas de investigación, innovación y/o Creación artística y cultural','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('51','null','null','Grupos de investigación','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('52','null','null','Semilleros de investigación','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('53','null','null','Investigadores reconocidos en el Sistema Nacional de Ciencia, Tecnología e Innovación','5','41');
--5.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','null','null','Proyectos de investigación, innovación y/o creación artística y cultural','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('55','null','null','Desarrollo de nuevos productos, procesos y usos de productos ya existentes','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('56','null','null','Capacidad para dar respuestas transformadoras a problemas locales, regionales y globales','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('57','null','null','Productos de investigación, innovación y/o creación artística y cultural','5','42');
--5.5
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','null','null','El Sistema de Investigaciones de la Universidad del Cauca','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','null','null','El Consejo de Investigaciones','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','null','null','Comités de Facultad para la Investigación y Posgrados','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','null','null','Grupos de Investigación','5','43');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('55','null','null','Mecanismos y estrategias para lograr la vinculación de la comunidad y el sector productivo, social, cultural, público y privado con el programa','6',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('56','null','null','Mecanismos y estrategias para lograr la articulación de los profesores y estudiantes con la dinámica social, productiva, creativa y cultural de su contexto','6',null);

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('57','null','null','Profesores del programa','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('58','null','null','Estrategias para la vinculación y permanencia de profesores','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('59','null','null','Dedicación de los profesores','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('60','null','null','Escalafón docente','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('61','null','null','Estrategias de desarrollo de profesores','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('62','null','null','Evaluación de profesores','7',null);

--7.1
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('63','null','null','Características del grupo de profesores','7','57');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('64','null','null','Vinculación de los profesores al programa académico','7','57');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('65','null','null','Dedicación y desarrollo de los profesores del programa','7','57');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('66','null','null','Dotación de medios educativos con los que cuenta el programa','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('67','null','null','Ambientes de aprendizaje físicos y/o virtuales','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('68','null','null','Mecanismos de capacitación y apropiación de los medios educativos para estudiantes y profesores','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('69','null','null','Plan de mantenimiento, actualización y reposición de los medios educativos','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('70','null','null','Disponibilidad de medios educativos','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('71','null','null','Estrategias para atender las barreras de acceso y las características de la población','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('72','null','null','Bibliotecas de la Universidad del Cauca','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('72','null','null','Servicios que prestan las Bibliotecas','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('73','null','null','Recursos bibliográficos de las Bibliotecas','8',null);

--8.9
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('74','null','null','Convenios Interinstitucionales','8','73');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('75','null','null','Revistas y bases de datos','8','73');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('76','null','null','Bibliografía','8','73');


INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('77','null','null','Infraestructura física del programa','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('78','null','null','Infraestructura tecnológica del programa','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('79','null','null','Disponibilidad de la infraestructura física y tecnológica','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('80','null','null','Escenarios de práctica (programas de salud)','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('81','null','null','Infraestructura de la Universidad del Cauca','9',null);



-- roles
INSERT INTO `rol` (rol_id,nombre_rol) VALUES ('1','ADMIN');
INSERT INTO `rol` (rol_id,nombre_rol) VALUES ('2','COORDINADOR');
INSERT INTO `rol` (rol_id,nombre_rol) VALUES ('3','SUPERUSUARIO');


-- usuarios
INSERT INTO `usuario` (rol_id,usuario_id,contrasena,correo,nombre,estado) VALUES ('1','1','$2a$10$Ew1CWKviOWFnlp0hhHd7xuv954E3lKtRV5Ee8hQe8UP6tmRSSDU92','aaa@gmail.com','juan',1);
INSERT INTO `usuario` (rol_id,usuario_id,contrasena,correo,nombre,estado) VALUES ('2','2','$2a$10$Ew1CWKviOWFnlp0hhHd7xuv954E3lKtRV5Ee8hQe8UP6tmRSSDU92','bbb@gmail.com','jose',1);
INSERT INTO `usuario` (rol_id,usuario_id,contrasena,correo,nombre,estado) VALUES ('3','3','$2a$10$Ew1CWKviOWFnlp0hhHd7xuv954E3lKtRV5Ee8hQe8UP6tmRSSDU92','ccc@gmail.com','pedro',1);

--Configuraciones


INSERT INTO `configuraciones` (nombre_variable, contenido) values ('rector','Deibar René Hurtado Herrera');
INSERT INTO `configuraciones` (nombre_variable, contenido) values ('vicerrector_academico','Aida Patricia González Nieva');
INSERT INTO `configuraciones` (nombre_variable, contenido) values ('vicerrector_administrativo','Jorge Enrique Barrera Moreno');
INSERT INTO `configuraciones` (nombre_variable, contenido) values ('vicerrector_investigaciones','Francisco José Pino Correa');
INSERT INTO `configuraciones` (nombre_variable, contenido) values ('vicerrector_cultura_bienestar','César Alfaro Mosquera Dorado');
INSERT INTO `configuraciones` (nombre_variable, contenido) values ('secretaria_general','Laura Ismenia Castellanos Vivas');















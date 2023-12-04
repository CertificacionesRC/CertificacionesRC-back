--ProgramaAcademico todo temrnar de ahcer lo demas
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('1','sistemas','PREGRADO', 'Ingeniería elelectrónica y telecomunicaciones');
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('2','electronica','PREGRADO', 'Ingeniería elelectrónica y telecomunicaciones');
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('3','civil','PREGRADO', 'Ingeniería Civil');

--RegistroCalificado
--todo verificar como nos deben llegar los colaboradores y autores
INSERT INTO `registro_calificado` (registro_calificado_id,autor,colaboradores,estado,fecha_creacion,programa_id) VALUES ('1','Deibar René Hurtado Herrera','Aida Patricia González Nieva, Jorge Enrique Barrera Moreno, Francisco José Pino Correa, César Alfaro Mosquera Dorado, Laura Ismenia Castellanos Vivas','PorAprobar','2023-10-11 19:33:42','1');

--Item
--todo validar el tema de la guia por cada item y subitem
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('1','','La institución deberá especificar la denominación o nombre del programa, en correspondencia con el título que se va a otorgar, el nivel de formación, los contenidos curriculares del programa y el perfil del egresado; lo anterior de acuerdo con la normatividad vigente.
Los programas técnicos profesionales y tecnológicos deben adoptar denominaciones que correspondan con las competencias propias de su campo de conocimiento, de tal manera que su denominación sea diferenciable y permita una clara distinción de las ocupaciones, disciplinas y profesiones.
Los programas de especialización deben definir denominaciones que correspondan al área específica de estudio. En el caso de los programas de maestría y doctorado podrán adoptar una denominación disciplinar o interdisciplinar.
Parágrafo. Las denominaciones no existentes en el Sistema Nacional de Información de la Educación Superior -SNIES deberán incluir una argumentación desde el (los) campo(s) del conocimiento y desde la pertinencia con las necesidades del país y de las regiones, en concordancia con el campo de ocupación, las normas que regulan el ejercicio de la profesión y el marco nacional de cualificaciones. Se podrá tener en cuenta referentes internacionales como los dados por: nomenclatura internacional de la Organización de las Naciones Unidas para la Educación, la Ciencia y la Cultura -UNESCO, estándares internacionales los campos de ciencia y tecnología, Clasificación Internacional Uniforme de Ocupaciones -CIUO, en inglés ISCO, entre otras.
','Denominación del Programa','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,registro_calificado_id) VALUES ('2','','La institución deberá presentar una justificación que sustente el contenido curricular, los perfiles de egreso y la (s) modalidad (es), en que se desea ofrecer el programa para que este sea pertinente al desarrollo social, cultural, ambiental, económico y científico, frente a las necesidades del país y la región, con fundamento en un estudio que por lo menos contenga los siguientes componentes:
a) El estado de la oferta de educación del área del programa, y de la ocupación, profesión, arte, u oficio, cuando sea del caso, en los ámbitos nacional y de las proyecciones del conocimiento en el contexto global.
b) Las necesidades de la región y del país que, según la propuesta, tengan relación directa con el programa en armonía con referentes internacionales, si estos vienen al caso, atendiendo a las dimensiones que determinan las modalidades (presencial, a distancia, virtual, dual u otros desarrollos que combinen e integren las anteriores modalidades) y las asociadas al registro calificado solicitado.
c) Una justificación de atributos o factores que constituyen los rasgos distintivos del programa con relación a los ya existentes en el área o las áreas del conocimiento y la(s) región(es) donde se desarrollará el programa, en coherencia con su naturaleza jurídica, tipología e identidad institucional.
Se espera que la IES plasme dentro del documento, los estudios que realizó para promover la posible apertura del programa, teniendo en cuenta la pertinencia y viabilidad en el entorno local, regional e internacional. Un análisis previo permitirá desarrollar todas las actividades asociadas a la planeación curricular y a la articulación institucional en torno del programa propuesto.
La propuesta académica está articulada a los Planes de Desarrollo locales, regionales y nacionales, y al Plan Estratégico de la Institución.
Utiliza información confiable, actualizada y verificable, con respeto a los derechos de autor.
Justifica no sólo el Programa sino también la metodología en la que se ofrece en relación con la misión y visión institucional expresada en el PEI.

Para programas en Renovación de Registro Calificado, además de lo anterior, verifique que la Justificación incluya:

Análisis con datos verificables del desempeño de los egresados del programa.
Evaluación del impacto que ha tenido el programa frente a la sociedad y su relación con otros programas de nivel superior de la Institución.
','Justificación del Programa','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('3','','','Aspectos Curriculares','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('4','','','Organización de actividades académicas y proceso formativo','EnProceso','1');

INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('5','','','Investigación, innovación y/o creación artística y cultural','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('6','','','Relación con el sector externo','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('7','','','Profesores','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('8','','','Medios Educativos','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('9','','','Infraestructura física y tecnológica','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('10','','','Bibliografía','EnProceso','1');

--SubItem
--1

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('2','','','Análisis de la coherencia entre la denominación, el título a otorgar, el nivel de formación, los contenidos curriculares y el perfil de egreso','1',null);
--2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('3','','','Estado de la oferta de educación en el área del programa a nivel nacional y global','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('4','','','Necesidades de la región y del país','EnProceso','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('5','','','Justificación de atributos o factores que constituyen los rasgos distintivos del programa','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('6','','','Análisis por periodos académicos de indicadores para los programas similares de referencia y las acciones adoptadas por la institución frente a los mismos','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('7','','','Pertinencia del programa académico frente al desarrollo, social, cultural, ambiental, económico y científico','2',null);
--2.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('8','','','Personas inscritas, admitidas y matriculadas','2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('9','','','Total de matriculados y graduados','2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('10','','','Tasas de deserción por cohorte','2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('11','','','Empleabilidad de los egresados','2','6');
--3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('12','','','Componentes Formativos','EnProceso','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('13','','','Componentes pedagógicos','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('14','','','Componentes de interacción','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('15','','','Conceptualización teórica y epistemológica del programa','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('16','','',' Mecanismos de evaluación','3',null);
--3.1
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('17','','','Plan general de estudios representado en Créditos Académicos','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('18','','','Resultados de aprendizaje del programa','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('19','','','Formación integral','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('20','','','Estrategias de flexibilización curricular','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('21','','','Perfil de egreso','3','12');
--3.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('22','','','Modelo pedagógico y didáctico del programa','3','13');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('23','','','Innovación pedagógica y didáctica del programa','3','13');
--3.3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('24','','','Articulación con los contextos locales, regionales y globales','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('25','','','Desarrollo de habilidades en estudiantes y profesores para la interacción nacional e internacional','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('26','','','Internacionalización del currículo','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('27','','','Competencias comunicativas en una segunda lengua','3','14');
--3.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('27','','','Fundamentos teóricos y conceptuales del programa','3','15');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('28','','','Objeto de estudio y formas de conocimiento del programa','3','15');
--3.5
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('29','','','Instrumentos de medición y seguimiento del desempeño de profesores','3','16');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('30','','','Instrumentos de medición y seguimiento del desempeño de estudiantes','3','16');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('31','','','Seguimiento y evaluación de resultados de aprendizaje','3','16');

ISRT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('32','','','Diseño y contenido curricular','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('33','','','Créditos por semestres','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('34','','','Proyecto educativo del programa','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('35','','','Mecanismos de interacción entre estudiante-profesor y estudiante-estudiante','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('36','','','Actividades académicas apoyadas en TIC','4',null);
--4.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('37','','','Número de créditos obligatorios','4','33');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('38','','','Número de créditos electivos','4','33');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('39','','','Declaración para el programa académico, de la incorporación de la investigación, innovación y/o creación artística y cultural para el desarrollo del conocimiento, según el nivel de formación del programa y la tipología y misión institucional','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('40','','','Formación en investigación','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('41','','','Organización de la investigación, innovación y/o creación artística y cultural en el programa','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('42','','','Resultados de la investigación, innovación y/o creación artística y cultural','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('43','','','Políticas Institucionales','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('44','','','Objetivos del Sistema de Investigaciones','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('45','','','Políticas del Sistema de Investigaciones','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('46','','','Estímulos para la Investigación','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('47','','','La incorporación de las TIC en la formación investigativa','5',null);
--5.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('48','','','Estrategias para la formación en investigación-creación-innovación','5','40');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('49','','','Desarrollo del pensamiento crítico y/o creativo','5','40');
--5.3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('50','','','Áreas, líneas y/o temáticas de investigación, innovación y/o Creación artística y cultural','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('51','','','Grupos de investigación','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('52','','','Semilleros de investigación','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('53','','','Investigadores reconocidos en el Sistema Nacional de Ciencia, Tecnología e Innovación','5','41');
--5.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','','','Proyectos de investigación, innovación y/o creación artística y cultural','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('55','','','Desarrollo de nuevos productos, procesos y usos de productos ya existentes','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('56','','','Capacidad para dar respuestas transformadoras a problemas locales, regionales y globales','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('57','','','Productos de investigación, innovación y/o creación artística y cultural','5','42');
--5.5
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','','','El Sistema de Investigaciones de la Universidad del Cauca','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','','','El Consejo de Investigaciones','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','','','Comités de Facultad para la Investigación y Posgrados','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('54','','','Grupos de Investigación','5','43');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('55','','','Mecanismos y estrategias para lograr la vinculación de la comunidad y el sector productivo, social, cultural, público y privado con el programa','6',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('56','','','Mecanismos y estrategias para lograr la articulación de los profesores y estudiantes con la dinámica social, productiva, creativa y cultural de su contexto','6',null);

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('57','','','Profesores del programa','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('58','','','Estrategias para la vinculación y permanencia de profesores','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('59','','','Dedicación de los profesores','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('60','','','Escalafón docente','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('61','','','Estrategias de desarrollo de profesores','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('62','','','Evaluación de profesores','7',null);

--7.1
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('63','','','Características del grupo de profesores','7','57');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('64','','','Vinculación de los profesores al programa académico','7','57');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('65','','','Dedicación y desarrollo de los profesores del programa','7','57');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('66','','','Dotación de medios educativos con los que cuenta el programa','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('67','','','Ambientes de aprendizaje físicos y/o virtuales','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('68','','','Mecanismos de capacitación y apropiación de los medios educativos para estudiantes y profesores','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('69','','','Plan de mantenimiento, actualización y reposición de los medios educativos','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('70','','','Disponibilidad de medios educativos','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('71','','','Estrategias para atender las barreras de acceso y las características de la población','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('72','','','Bibliotecas de la Universidad del Cauca','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('72','','','Servicios que prestan las Bibliotecas','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('73','','','Recursos bibliográficos de las Bibliotecas','8',null);

--8.9
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('74','','','Convenios Interinstitucionales','8','73');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('75','','','Revistas y bases de datos','8','73');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('76','','','Bibliografía','8','73');


INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('77','','','Infraestructura física del programa','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('78','','','Infraestructura tecnológica del programa','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('79','','','Disponibilidad de la infraestructura física y tecnológica','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('80','','','Escenarios de práctica (programas de salud)','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,item_id,parent_id) VALUES ('81','','','Infraestructura de la Universidad del Cauca','9',null);



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















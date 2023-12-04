--ProgramaAcademico todo temrnar de ahcer lo demas
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('1','sistemas','PREGRADO', 'Ingeniería elelectrónica y telecomunicaciones');
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('2','electronica','PREGRADO', 'Ingeniería elelectrónica y telecomunicaciones');
INSERT INTO `programa_academico` (programa_academico_id,nombre,tipo, facultad) VALUES ('3','civil','PREGRADO', 'Ingeniería Civil');

--RegistroCalificado
--todo verificar como nos deben llegar los colaboradores y autores
INSERT INTO `registro_calificado` (registro_calificado_id,autor,colaboradores,estado,fecha_creacion,programa_id) VALUES ('1','Deibar René Hurtado Herrera','Aida Patricia González Nieva, Jorge Enrique Barrera Moreno, Francisco José Pino Correa, César Alfaro Mosquera Dorado, Laura Ismenia Castellanos Vivas','PorAprobar','2023-10-11 19:33:42','1');

--Item
--todo validar el tema de la guia por cada item y subitem
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('1','','La institución deberá especificar la denominación o nombre del programa, en correspondencia con el título que se va a otorgar, el nivel de formación, los contenidos curriculares del programa y el perfil del egresado, lo anterior de acuerdo con la normatividad vigente. Los programas técnicos profesionales y tecnológicos deben adoptar denominaciones que correspondan con las competencias propias de su campo de conocimiento, de tal manera que su denominación sea diferenciable y permita una clara distinción de las ocupaciones, disciplinas y profesiones. Los programas de especialización deben definir denominaciones que correspondan al área específica de estudio. En el caso de los programas de maestría y doctorado podrán adoptar una denominación disciplinar o interdisciplinar. Parágrafo. Las denominaciones no existentes en el Sistema Nacion de Información de la Educación Superior -SNIES deberán incluir una argumentación desde el (los) campo(s) del conocimiento y desde la pertinencia con las necesidades del país y de las regiones, en concordancia con el campo de ocupación, las normas que regulan el ejercicio de la profesión y el marco nacional de cualificaciones. Se podrá tener en cuenta referentes internacionales como los dados por: nomenclatura internacional de la Organización de las Naciones Unidas para la Educación, la Ciencia y la Cultura -UNESCO, estándares internacionales los campos de ciencia y tecnología, Clasificación Internacional Uniforme de Ocupaciones -CIUO, en inglés ISCO, entre otras.','Denominación del Programa','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('2','','La institución deberá presentar una justificación que sustente el contenido curricular, los perfiles de egreso y la (s) modalidad (es), en que se desea ofrecer el programa para que este sea pertinente al desarrollo social, cultural, ambiental, económico y científico, frente a las necesidades del país y la región, con fundamento en un estudio que por lo menos contenga los siguientes componentes: a) El estado de la oferta de educación del área del programa, y de la ocupación, profesión, arte, u oficio, cuando sea del caso, en los ámbitos nacional y de las proyecciones del conocimiento en el contexto global. b) Las necesidades de la región y del país que, según la propuesta, tengan relación directa con el programa en armonía con referentes internacionales, si estos vienen al caso, atendiendo a las dimensiones que determinan las modalidades (presencial, a distancia, virtual, dual u otros desarrollos que combinen e integren las anteriores modalidades) y las asociadas al registro calificado solicitado. c) Una justificación de atributos o factores que constituyen los rasgos distintivos del programa con relación a los ya existentes en el área o las áreas del conocimiento y la(s) región(es) donde se desarrollará el programa, en coherencia con su naturaleza jurídica, tipología e identidad institucional. Se espera que la IES plasme dentro del documento, los estudios que realizó para promover la posible apertura del programa, teniendo en cuenta la pertinencia y viabilidad en el entorno local, regional e internacional. Un análisis previo permitirá desarrollar todas las actividades asociadas a la planeación curricular y a la articulación institucional en torno del programa propuesto. La propuesta académica está articulada a los Planes de Desarrollo locales, regionales y nacionales, y al Plan Estratégico de la Institución. Utiliza información confiable, actualizada y verificable, con respeto a los derechos de autor. Justifica no sólo el Programa sino también la metodología en la que se ofrece en relación con la misión y visión institucional expresada en el PEI. Para programas en Renovación de Registro Calificado, además de lo anterior, verifique que la Justificación incluya: Análisis con datos verificables del desempeño de los egresados del programa. Evaluación del impacto que ha tenido el programa frente a la sociedad y su relación con otros programas de nivel superior de la Institución.','Justificación del Programa','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('3','','La institución deberá diseñar el contenido curricular del programa según el área de conocimiento y en coherencia con las modalidades (presencial, a distancia, virtual, dual u otros desarrollos que combinen e integren las anteriores modalidades), los niveles de formación, su naturaleza jurídica, tipología e identidad institucional. El cual deberá contar, por lo menos con: Componente formativos, Componentes pedagógicos, Componentes de interacción, Conceptualización teórica y epistemológica del program,  Mecanismos de evaluación ','Aspectos Curriculares','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('4','','La institución deberá establecer en el programa, la organización de las actividades y la interacción mismas, de acuerdo con el diseño y contenido curricular, en coherencia con las modalidades, los niveles formación, la naturaleza jurídica, la tipología y la identidad institucional. Para actividad de formación incluida en el plan estudios se deben los créditos y discriminar las horas trabajo independiente y las de acompañamiento directo del docente, acorde con el sistema institucional de créditos.Los programas del área de ciencias de la salud deben prever las prácticas formativas, supervisadas por profesores responsables de ellas y disponer de los escenarios apropiados para su realización, y estará sujetos a lo dispuesto en el decreto 2376 de Julio de 2010 por medio del cual se regula la relación docencia-servicio para los programas de formación de talento humano del área de Salud, en concordancia con la ley 1164 de 2007, el modelo de evaluación de la relación docencia, servicio y demás normas vigentes sobre la materia. Aquellos programas que contengan prácticas profesionales con asignación de créditos académicos   o   como   opción   de   grado, es   necesario   presentar   las estrategias pedagógicas para su desarrollo y evaluación, así como una relación y el soporte de los convenios que evidencie la capacidad para atender el número de estudiantes proyectados o matriculados por cohorte. Para el caso de los programas a distancia o virtuales, se hace necesario detallar la manera como el desarrollo de las actividades académicas estará relacionado con el trabajo con acompañamiento y el trabajo independiente o autónomo. Así mismo, el tiempo que se dedicará para cada escenario de práctica, los sitios de práctica y la forma como se llevarán a cabo. ','Organización de actividades académicas y proceso formativo','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('5','','La institución deberá establecer en el programa las estrategias para la formación en investigación- creación que le permitan a profesores y estudiantes estar en contacto con los desarrollos disciplinarios e interdisciplinarios, la creación artística, los avances tecnológicos y el campo disciplinar más actualizado, de tal forma que se desarrolle el pensamiento crítico y/o creativo. El programa en coherencia con el nivel de formación, las modalidades (presencial, a distancia, virtual, dual u otros desarrollos que combinen e integren las anteriores modalidades), con la naturaleza jurídica, tipología, identidad y misión institucional, propenderá a que sus resultados de investigación contribuyan a la transformación social de las dinámicas que aporten a la construcción del país. Según la declaración explícita que realice el programa con relación a la incorporación de la investigación para el desarrollo del conocimiento, el programa deberá definir las áreas, líneas o temáticas de investigación en las que se enfocarán los esfuerzos y proyectos. Lo anterior, teniendo en cuenta los siguientes propósitos de investigación: a) La comprensión teórica para la formación de un pensamiento innovador, con capacidad de construir, ejecutar, controlar y operar los medios y procesos para la solución de problemas que demandan los sectores productivos y de servicios del país. b) La incorporación de la formación investigativa de los estudiantes en concordancia con el nivel educativo y sus objetivos, el uso de las tecnologías de la información y de la comunicación. c) El desarrollo de nuevos productos, procesos y usos de productos ya existentes. d) La capacidad para dar respuestas transformadoras a problemas locales, regionales y globales, e indagar sobre la realidad social y ambiental, entre otros, a partir del uso del conocimiento como herramienta de desarrollo. e) Aquellos programas que hicieron explícita la incorporación de la investigación, innovación y/o creación artística deberán evidenciar sus resultados de acuerdo con los lineamientos establecidos por el sistema nacional de ciencia y tecnología u otros afines.','Investigación, innovación y/o creación artística y cultural','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('6','','','Relación con el sector externo','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('7','','','Profesores','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('8','','','Medios Educativos','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('9','','','Infraestructura física y tecnológica','EnProceso','1');
INSERT INTO `item` (item_id,contenido,guia,nombre,estado,registro_calificado_id) VALUES ('10','','','Bibliografía','EnProceso','1');

--SubItem
--1

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('1','','El Programa de… fue creado mediante el Acuerdo N° XXXX…, expedido por el Consejo Superior de la Universidad del Cauca, con Registro Calificado otorgado por medio de la Resolución N° XXXX… del (día) de (mes) del (año). (Información solo para programas que soliciten renovación) de conformidad con su naturaleza universitaria, nivel profesional, modalidad… y metodología… otorga el título de…','Información básica del programa','EnProceso','1',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('2','','','Análisis de la coherencia entre la denominación, el título a otorgar, el nivel de formación, los contenidos curriculares y el perfil de egreso','EnProceso','1',null);
--2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('3','','','Estado de la oferta de educación en el área del programa a nivel nacional y global','EnProceso','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('4','','','Necesidades de la región y del país','EnProceso','EnProceso','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('5','','','Justificación de atributos o factores que constituyen los rasgos distintivos del programa','EnProceso','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('6','','','Análisis por periodos académicos de indicadores para los programas similares de referencia y las acciones adoptadas por la institución frente a los mismos','EnProceso','2',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('7','','','Pertinencia del programa académico frente al desarrollo, social, cultural, ambiental, económico y científico','EnProceso','2',null);
--2.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('8','','','Personas inscritas, admitidas y matriculadas''EnProceso',,'2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('9','','','Total de matriculados y graduados''EnProceso',,'2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('10','','','Tasas de deserción por cohorte''EnProceso',,'2','6');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('11','','','Empleabilidad de los egresados''EnProceso',,'2','6');
--3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('12','','Se refieren a la definición del plan general de estudios, deberá estar representado en créditos académicos conforme con los resultados de aprendizaje proyectados, la formación integral, las actividades académicas que evidencien estrategias de flexibilización curricular, y los perfiles de egreso, en armonía con las habilidades del contexto internacional, nacional, y local orientadas desarrollo de las capacidades para aprender a aprender.','Componentes Formativos','EnProceso','EnProceso','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('13','','','Componentes pedagógicos','EnProceso','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('14','','','Componentes de interacción','EnProceso','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('15','','','Conceptualización teórica y epistemológica del programa','EnProceso','3',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('16','','',' Mecanismos de evaluación','EnProceso','3',null);
--3.1
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('17','','','Plan general de estudios representado en Créditos Académicos','EnProceso','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('18','','','Resultados de aprendizaje del programa','EnProceso','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('19','','','Formación integral','EnProceso','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('20','','','Estrategias de flexibilización curricular','EnProceso','3','12');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('21','','','Perfil de egreso','EnProceso','3','12');
--3.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('22','','','Modelo pedagógico y didáctico del programa','EnProceso','3','13');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('23','','','Innovación pedagógica y didáctica del programa','EnProceso','3','13');
--3.3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('24','','','Articulación con los contextos locales, regionales y globales','EnProceso','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('25','','','Desarrollo de habilidades en estudiantes y profesores para la interacción nacional e internacional','EnProceso','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('26','','','Internacionalización del currículo','EnProceso','3','14');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('27','','','Competencias comunicativas en una segunda lengua','EnProceso','3','14');
--3.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('27','','','Fundamentos teóricos y conceptuales del programa','EnProceso','3','15');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('28','','','Objeto de estudio y formas de conocimiento del programa','EnProceso','3','15');
--3.5
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('29','','','Instrumentos de medición y seguimiento del desempeño de profesores','EnProceso','3','16');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('30','','','Instrumentos de medición y seguimiento del desempeño de estudiantes','EnProceso','3','16');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('31','','','Seguimiento y evaluación de resultados de aprendizaje','EnProceso','3','16');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('32','','','Diseño y contenido curricular','EnProceso','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('33','','','Créditos por semestres','EnProceso','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('34','','','Proyecto educativo del programa','EnProceso','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('35','','','Mecanismos de interacción entre estudiante-profesor y estudiante-estudiante','EnProceso','4',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('36','','','Actividades académicas apoyadas en TIC','EnProceso','4',null);
--4.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('37','','','Número de créditos obligatorios','EnProceso','4','33');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('38','','','Número de créditos electivos','EnProceso','4','33');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('39','','','Declaración para el programa académico, de la incorporación de la investigación, innovación y/o creación artística y cultural para el desarrollo del conocimiento, según el nivel de formación del programa y la tipología y misión institucional','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('40','','','Formación en investigación','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('41','','','Organización de la investigación, innovación y/o creación artística y cultural en el programa','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('42','','','Resultados de la investigación, innovación y/o creación artística y cultural','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('43','','','Políticas Institucionales','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('44','','','Objetivos del Sistema de Investigaciones','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('45','','','Políticas del Sistema de Investigaciones','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('46','','','Estímulos para la Investigación','EnProceso','5',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('47','','','La incorporación de las TIC en la formación investigativa','EnProceso','5',null);
--5.2
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('48','','','Estrategias para la formación en investigación-creación-innovación','EnProceso','5','40');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('49','','','Desarrollo del pensamiento crítico y/o creativo','EnProceso','5','40');
--5.3
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('50','','','Áreas, líneas y/o temáticas de investigación, innovación y/o Creación artística y cultural','EnProceso','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('51','','','Grupos de investigación','EnProceso','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('52','','','Semilleros de investigación','EnProceso','5','41');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('53','','','Investigadores reconocidos en el Sistema Nacional de Ciencia, Tecnología e Innovación','EnProceso','5','41');
--5.4
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('54','','','Proyectos de investigación, innovación y/o creación artística y cultural','EnProceso','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('55','','','Desarrollo de nuevos productos, procesos y usos de productos ya existentes','EnProceso','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('56','','','Capacidad para dar respuestas transformadoras a problemas locales, regionales y globales','EnProceso','5','42');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('57','','','Productos de investigación, innovación y/o creación artística y cultural','EnProceso','5','42');
--5.5
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('54','','','El Sistema de Investigaciones de la Universidad del Cauca','EnProceso','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('54','','','El Consejo de Investigaciones','EnProceso','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('54','','','Comités de Facultad para la Investigación y Posgrados','EnProceso','5','43');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('54','','','Grupos de Investigación','EnProceso','5','43');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('55','','','Mecanismos y estrategias para lograr la vinculación de la comunidad y el sector productivo, social, cultural, público y privado con el programa','EnProceso','6',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('56','','','Mecanismos y estrategias para lograr la articulación de los profesores y estudiantes con la dinámica social, productiva, creativa y cultural de su contexto','EnProceso','6',null);

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('57','','','Profesores del programa','EnProceso','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('58','','','Estrategias para la vinculación y permanencia de profesores','EnProceso','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('59','','','Dedicación de los profesores','EnProceso','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('60','','','Escalafón docente','EnProceso','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('61','','','Estrategias de desarrollo de profesores','EnProceso','7',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('62','','','Evaluación de profesores','EnProceso','7',null);

--7.1
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('63','','','Características del grupo de profesores','EnProceso','7','57');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('64','','','Vinculación de los profesores al programa académico','EnProceso','7','57');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('65','','','Dedicación y desarrollo de los profesores del programa','EnProceso','7','57');

INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('66','','','Dotación de medios educativos con los que cuenta el programa','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('67','','','Ambientes de aprendizaje físicos y/o virtuales','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('68','','','Mecanismos de capacitación y apropiación de los medios educativos para estudiantes y profesores','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('69','','','Plan de mantenimiento, actualización y reposición de los medios educativos','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('70','','','Disponibilidad de medios educativos','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('71','','','Estrategias para atender las barreras de acceso y las características de la población','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('72','','','Bibliotecas de la Universidad del Cauca','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('72','','','Servicios que prestan las Bibliotecas','EnProceso','8',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('73','','','Recursos bibliográficos de las Bibliotecas','EnProceso','8',null);

--8.9
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('74','','','Convenios Interinstitucionales','EnProceso','8','73');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('75','','','Revistas y bases de datos','EnProceso','8','73');
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('76','','','Bibliografía','EnProceso','8','73');


INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('77','','','Infraestructura física del programa','EnProceso','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('78','','','Infraestructura tecnológica del programa','EnProceso','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('79','','','Disponibilidad de la infraestructura física y tecnológica','EnProceso','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('80','','','Escenarios de práctica (programas de salud)','EnProceso','9',null);
INSERT INTO `sub_item` (subitem_id,contenido,guia,nombre,estado,item_id,parent_id) VALUES ('81','','','Infraestructura de la Universidad del Cauca','EnProceso','9',null);



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















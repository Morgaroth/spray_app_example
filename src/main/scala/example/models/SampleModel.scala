package example.models

import spray.json.DefaultJsonProtocol

/**
 * modele, różne, w swoich projektach stosowalem dalszą separację modeli na http (request-response), db itd...
 * klasa definuje klasę,
 * w traicie CosTamModel mamy marshaller, który jest potem niejawnie używany do (de)serializacji obiektów
 */
case class SampleModel(
                        value1: Int,
                        value2: String,
                        value3: Long
                        )

object SampleModel {
}

trait SampleModelModel extends DefaultJsonProtocol {
  implicit val SampleModelRootJsonFormat = jsonFormat3(SampleModel.apply)
}

object SampleModelModel extends SampleModelModel


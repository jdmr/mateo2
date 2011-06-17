package contabilidad



import org.junit.*
import grails.test.mixin.*


@TestFor(ServicioController)
@Mock(Servicio)
class ServicioControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/servicio/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.servicioInstanceList.size() == 0
        assert model.servicioInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.servicioInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.servicioInstance != null
        assert view == '/servicio/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/servicio/show/1'
        assert controller.flash.message != null
        assert Servicio.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/servicio/list'


        def servicio = new Servicio()

        // TODO: populate domain properties

        assert servicio.save() != null

        params.id = servicio.id

        def model = controller.show()

        assert model.servicioInstance == servicio
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/servicio/list'


        def servicio = new Servicio()

        // TODO: populate valid domain properties

        assert servicio.save() != null

        params.id = servicio.id

        def model = controller.edit()

        assert model.servicioInstance == servicio
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/servicio/list'

        response.reset()


        def servicio = new Servicio()

        // TODO: populate valid domain properties

        assert servicio.save() != null

        // test invalid parameters in update
        params.id = servicio.id

        controller.update()

        assert view == "/servicio/edit"
        assert model.servicioInstance != null

        servicio.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/servicio/show/$servicio.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/servicio/list'

        response.reset()

        def servicio = new Servicio()

        // TODO: populate valid domain properties
        assert servicio.save() != null
        assert Servicio.count() == 1

        params.id = servicio.id

        controller.delete()

        assert Servicio.count() == 0
        assert Servicio.get(servicio.id) == null
        assert response.redirectedUrl == '/servicio/list'


    }


}
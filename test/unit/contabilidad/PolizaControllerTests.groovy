package contabilidad



import org.junit.*
import grails.test.mixin.*


@TestFor(PolizaController)
@Mock(Poliza)
class PolizaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/poliza/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.polizaInstanceList.size() == 0
        assert model.polizaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.polizaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.polizaInstance != null
        assert view == '/poliza/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/poliza/show/1'
        assert controller.flash.message != null
        assert Poliza.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'


        def poliza = new Poliza()

        // TODO: populate domain properties

        assert poliza.save() != null

        params.id = poliza.id

        def model = controller.show()

        assert model.polizaInstance == poliza
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'


        def poliza = new Poliza()

        // TODO: populate valid domain properties

        assert poliza.save() != null

        params.id = poliza.id

        def model = controller.edit()

        assert model.polizaInstance == poliza
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'

        response.reset()


        def poliza = new Poliza()

        // TODO: populate valid domain properties

        assert poliza.save() != null

        // test invalid parameters in update
        params.id = poliza.id

        controller.update()

        assert view == "/poliza/edit"
        assert model.polizaInstance != null

        poliza.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/poliza/show/$poliza.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'

        response.reset()

        def poliza = new Poliza()

        // TODO: populate valid domain properties
        assert poliza.save() != null
        assert Poliza.count() == 1

        params.id = poliza.id

        controller.delete()

        assert Poliza.count() == 0
        assert Poliza.get(poliza.id) == null
        assert response.redirectedUrl == '/poliza/list'


    }


}